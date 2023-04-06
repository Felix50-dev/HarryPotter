package com.example.harrypotter.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.R
import com.example.harrypotter.data.models.Character
import com.example.harrypotter.navigation.NavigationDestination
import com.example.harrypotter.ui.theme.HarryPotterTheme

object MainScreenDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory),
    onItemClick: (String) -> Unit
) {

    when(val mainScreenUiState = mainScreenViewModel.mainScreenUiState) {
        is MainScreenUiState.Loading -> LoadingScreen(modifier)
        is MainScreenUiState.Success -> Characters(mainScreenUiState.characters, onItemClick = { onItemClick })
        is MainScreenUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun Characters(characters: List<Character>,onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(characters) { character ->
            CharacterItem(character = character, modifier = Modifier, onItemClick)
        }
    }
}
@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(74.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onItemClick(character.name) }
        ) {
            CharacterInformation(name = character.name, house = character.house)
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .weight(1f)
            )
            CharacterIcon(character)
        }
    }
}

@Composable
fun CharacterInformation(
    name: String, house: String
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = house,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun CharacterIcon(character: Character, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(character.image)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        contentDescription = "image description",
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}
@Preview
@Composable
fun MainScreenPreview() {
    HarryPotterTheme {
        val mockData = List(10) { Character("", "", "","", "", "") }
        //Characters(mockData)
    }

}