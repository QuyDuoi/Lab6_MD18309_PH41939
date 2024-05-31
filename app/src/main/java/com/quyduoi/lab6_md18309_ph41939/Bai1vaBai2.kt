package com.quyduoi.lab6_md18309_ph41939

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class Movie(
    val title: String,
    val year: String,
    val posterUrl: String,
    val duration: String,
    val releaseDate: String,
    val genre: String,
    val shotDescription: String
) {
    companion object {
        fun getSampleMovies() = listOf(
            Movie(
                title = "The Shawshank Redemption",
                year = "1994",
                posterUrl = "https://www.themoviedb.org/t/p/original/gUgdoneM2fzsT6Qx7GhGU4kxUgx.jpg",
                duration = "142 minutes",
                releaseDate = "September 22, 1994",
                genre = "Drama",
                shotDescription = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
            ),
            Movie(
                title = "The Godfather",
                year = "1972",
                posterUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                duration = "175 minutes",
                releaseDate = "March 24, 1972",
                genre = "Crime, Drama",
                shotDescription = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."
            ),
            Movie(
                title = "The Dark Knight",
                year = "2008",
                posterUrl = "https://www.themoviedb.org/t/p/original/eP5NL7ZlGoW9tE9qnCdHpOLH1Ke.jpg",
                duration = "152 minutes",
                releaseDate = "July 18, 2008",
                genre = "Action, Crime, Drama",
                shotDescription = "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham."
            ),
            Movie(
                title = "Pulp Fiction",
                year = "1994",
                posterUrl = "https://www.themoviedb.org/t/p/original/pbWgQPC6l9pkpEpi3WNRSfWYNP6.jpg",
                duration = "154 minutes",
                releaseDate = "October 14, 1994",
                genre = "Crime, Drama",
                shotDescription = "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
            ),
            Movie(
                title = "The Lord of the Rings: The Return of the King",
                year = "2003",
                posterUrl = "https://image.tmdb.org/t/p/original/uexxR7Kw1qYbZk0RYaF9Rx5ykbj.jpg",
                duration = "201 minutes",
                releaseDate = "December 17, 2003",
                genre = "Action, Adventure, Drama",
                shotDescription = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
            )
        )
    }
}

enum class ListType {
    ROW, COLUMN, GRID
}
@Preview(showBackground = true)
@Composable
fun Bai1Preview() {
    Bai1(movies = Movie.getSampleMovies())
}

@Composable
fun Bai1(movies: List<Movie>) {
    var listType by remember { mutableStateOf(ListType.ROW) }
    Column {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { listType = ListType.ROW }) {
                Text("Row")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.COLUMN }) {
                Text("Column")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.GRID }) {
                Text("Grid")
            }
        }
        when (listType) {
            ListType.ROW -> MovieRow(movies)
            ListType.COLUMN -> MovieColumn(movies)
            ListType.GRID -> MovieGrid(movies)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor =
        Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation =
        6.dp),
    ) {
        Column(
            modifier = Modifier.then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.wrapContentHeight().fillMaxWidth()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(
                    label = "Thời lượng: ", value =
                    movie.duration)
                BoldValueText(
                    label = "Khởi chiếu: ", value =
                    movie.releaseDate)
            }
        }
    }
}
@Composable
fun MovieColumnItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor =
        Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation =
        6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .then(getItemSizeModifier(listType))
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(
                    label = "Thời lượng: ", value =
                    movie.duration)
                BoldValueText(
                    label = "Khởi chiếu: ", value =
                    movie.releaseDate)
                BoldValueText(
                    label = "Thể loại: ", value =
                    movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom =
                    2.dp)
                )
                Text(
                    text = movie.shotDescription,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
    }
}
@Composable
fun BoldValueText(
    label: String, value: String, style: androidx.compose.ui.text.TextStyle =
        MaterialTheme.typography.bodySmall
) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}
@Composable
private fun getItemSizeModifier(listType: ListType): Modifier {
    return when (listType) {
        ListType.ROW -> Modifier.width(175.dp)
        ListType.COLUMN -> Modifier
            .width(130.dp)
        ListType.GRID -> Modifier
            .fillMaxWidth()
    }
}
@Composable
fun MovieRow(movies: List<Movie>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.ROW )
        }
    }
}
@Composable
fun MovieColumn(movies: List<Movie>) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieColumnItem(movie = movies[index], listType =
            ListType.COLUMN)
        }
    }
}
@Composable
fun MovieGrid(movies: List<Movie>) {
    val gridState = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], listType = ListType.GRID)
        }
    }
}