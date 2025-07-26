package com.saberoueslati.devbuddy.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    type: AppTextFieldType,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue: String ->
            onValueChange.invoke(newValue)
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF9ca39f)
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF1F2937),
            errorContainerColor = Color(0xFF1F2937),
            focusedContainerColor = Color(0xFF1F2937),
            disabledContainerColor = Color(0xFF1F2937),
            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedBorderColor = Color(0xFF374151),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        maxLines = 1,
        leadingIcon = type.leadingIcon,
        trailingIcon = type.trailingIcon
    )
}

sealed class AppTextFieldType {
    internal open val leadingIcon: (@Composable (() -> Unit))? = null
    internal open val trailingIcon: (@Composable (() -> Unit))? = null

    object Regular : AppTextFieldType()
    class Search(val onClearClicked: () -> Unit) : AppTextFieldType() {
        override val leadingIcon: @Composable (() -> Unit)? = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF9CA3AF)
            )
        }

        override val trailingIcon: @Composable (() -> Unit)? = {
            IconButton(onClick = onClearClicked) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear",
                    tint = Color(0xFF9CA3AF)
                )
            }
        }
    }
}