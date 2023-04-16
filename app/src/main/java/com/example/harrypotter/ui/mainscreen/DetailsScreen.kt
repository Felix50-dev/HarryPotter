package com.example.harrypotter.ui.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.HarryPotterTopAppBar
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
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailsScreenViewModel: DetailsScreenViewModel = viewModel(factory = DetailsScreenViewModel.Factory),
    navigateBack: () -> Unit,
) {
    val character = detailsScreenViewModel.character

    Scaffold(
        topBar = {
            HarryPotterTopAppBar(
                title = stringResource(DetailsScreenDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "image description",
                modifier = modifier
                    .clip(RoundedCornerShape(50))
                    .size(150.dp)
                    .padding(paddingValues = innerPadding),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.size(16.dp))
            CharacterDetails(character)
        }
    }

}

@Composable
fun CharacterDetails(character: Character, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.h4,
        )
        Spacer(modifier = modifier.size(8.dp))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(
                Icons.Filled.Home,
                contentDescription = "home",
                tint = Color.Red,
                modifier = modifier.align(Alignment.Bottom)
            )
            Spacer(modifier = modifier.size(8.dp))
            Text(
                text = character.house,
                style = MaterialTheme.typography.h5
            )
        }
        character.alternateNames?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = modifier.size(8.dp))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(
                Icons.Filled.Person,
                contentDescription = "person",
                tint = Color.Red,
                modifier = modifier.align(Alignment.Bottom)
            )
            Spacer(modifier = modifier.size(8.dp))
            Text(
                text = character.actor,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}
