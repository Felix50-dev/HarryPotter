package com.example.harrypotter.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.R
import com.example.harrypotter.data.models.Character
import com.example.harrypotter.navigation.NavigationDestination

object DetailsScreenDestination : NavigationDestination {
    override val route = "Character"
    override val titleRes = R.string.Character
    const val characterIdArg = "name"
    val routeWithArgs = "$route/{$characterIdArg}"
}

@Composable
fun DetailsScreen(detailsScreenViewModel: DetailsScreenViewModel = viewModel(factory = DetailsScreenViewModel.Factory)) {
    val character = detailsScreenViewModel.character
    DetailsScreenAppearance(character = character)
}

@Composable
fun DetailsScreenAppearance(character: Character) {
    CharacterImage(character)
    CharacterDetails(name = character.name, house = character.house, alternateNames = character.alternateNames, wand = character.name, actor = character.actor)
}

@Composable
fun CharacterImage(character: Character, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(character.image)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        alignment = Alignment.Center,
        contentDescription = "image description",
        modifier = modifier
            .size(128.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CharacterDetails(name: String, house: String, alternateNames: String, wand: String, actor: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = house,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = alternateNames,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = wand,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = actor,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    val mockData = Character("","","","","","")
    DetailsScreenAppearance(mockData)
}