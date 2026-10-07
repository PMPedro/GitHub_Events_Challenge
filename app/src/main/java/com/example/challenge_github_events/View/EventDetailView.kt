package com.example.ecabs_challenge_pedro_martins.View

import android.text.InputFilter
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.ecabs_challenge_pedro_martins.R
import com.example.ecabs_challenge_pedro_martins.ViewModel.EventDetailViewModel
import com.example.ecabs_challenge_pedro_martins.ui.theme.AppElevation
import com.example.ecabs_challenge_pedro_martins.ui.theme.AppSpacing

@Composable
fun EventDetailView(
    viewModel: EventDetailViewModel
) {
    val data = viewModel.selectedEvent.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        topPart(
            avatarUrl = data.value?.actor?.avatar_url,
            login = data.value?.actor?.login
        )
        Column(
            modifier = Modifier
                .padding(horizontal = AppSpacing.M)
                .padding(top = AppSpacing.M),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.S)
        ) {
            EventInfo(
                createdAt = data.value?.created_at,
                type = data.value?.type,
                id = data.value?.id
            )

            RepoInfo(
                id = data.value?.repo?.id,
                name = data.value?.repo?.name
            )
        }
    }
}


@Composable
fun topPart(
    avatarUrl: String?,
    login: String?
) {
    Surface(
        tonalElevation = AppElevation.Level4,
        shadowElevation = AppElevation.Level5
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "User avatar",
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .border(
                        3.dp,
                        MaterialTheme.colorScheme.onPrimary,
                        CircleShape
                    )
            )
            Spacer(Modifier.padding(AppSpacing.S))
            Text(
                login ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.15f)
            ) {
                Text(
                    "GitHub Actor",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun EventInfo(
    createdAt: String?,
    type: String?,
    id: String?
) {
    Surface(
        tonalElevation = AppElevation.Level2,
        shadowElevation = AppElevation.Level2,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.M)
        ) {
            Text(
                "Event info",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))

            LabelRow(
                label = "Date",
                value = createdAt ?: "—"
            )
            HorizontalDivider(
                color = MaterialTheme
                    .colorScheme
                    .outlineVariant
                    .copy(alpha = 0.4f)
            )

            LabelRow(
                label = "Type",
                value = type ?: "—"
            )

            HorizontalDivider(
                color = MaterialTheme
                    .colorScheme
                    .outlineVariant
                    .copy(alpha = 0.4f)
            )

            LabelRow(
                label = "ID",
                value = id ?: "—"
            )
        }
    }
}


@Composable
fun RepoInfo(
    id: Long?,
    name: String?
) {
    Surface(
        tonalElevation = AppElevation.Level2,
        shadowElevation = AppElevation.Level2,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.M)
        ) {
            Text(
                "Repository",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            LabelRow(
                label = "Name",
                value = name ?: "—"
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            )

            LabelRow(
                label = "ID", value = "$id"
            )
        }
    }
}

@Composable
fun LabelRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppSpacing.S),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

