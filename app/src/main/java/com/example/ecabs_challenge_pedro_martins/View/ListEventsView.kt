package com.example.ecabs_challenge_pedro_martins.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecabs_challenge_pedro_martins.Data.utils.UiState
import com.example.ecabs_challenge_pedro_martins.Model.GithubEvent
import com.example.ecabs_challenge_pedro_martins.ViewModel.ListEventsViewModel
import com.example.ecabs_challenge_pedro_martins.ui.theme.AppElevation
import com.example.ecabs_challenge_pedro_martins.ui.theme.AppSpacing

@Composable
fun ListEventsScreen(
    viewModel: ListEventsViewModel,
    onDetailClickNavigation: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
    {
        TopPart()
        Spacer(Modifier.height(AppSpacing.M))
        ListEvents(
            state = state,
            onItemClick = { event ->
            viewModel.setEvent(event)
            onDetailClickNavigation()
        })
    }
}

fun Shape() = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width * 0.8f, size.height)
    lineTo(size.width * 0.2f, size.height)
    close()
}

@Composable
fun TopPart() {
    Surface(
        shape = Shape(),
        tonalElevation = AppElevation.Level5,
        shadowElevation = AppElevation.Level5
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(Shape())
                .background(MaterialTheme.colorScheme.primary),

            ) {
            TopText()
        }
    }
}

@Composable
fun TopText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            "List of Events",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ListEvents(
    state: UiState<List<GithubEvent>> ,
    onItemClick: (GithubEvent) -> Unit
) {
        when (state) {
            is UiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }

            is UiState.Error -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Something Went Wrong ${state.message}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = AppSpacing.M,
                        vertical = AppSpacing.S),
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.M)
                ) {
                    items(state.data) { item ->
                        ListEventItem(item , onItemClick)
                    }
                }
            }
        }
}

@Composable
fun ListEventItem(
    item: GithubEvent ,
    onItemClick: (GithubEvent) -> Unit
) {
    Surface(
        tonalElevation = AppElevation.Level2,
        shadowElevation = AppElevation.Level2,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable {
                onItemClick(item)
            }

    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = AppSpacing.M,
                    vertical = AppSpacing.S),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                item.type ?: "—",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                item.created_at ?: "—",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "ID: ${item.id}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun prevListEventView() {
    //  ListEventsScreen()
}