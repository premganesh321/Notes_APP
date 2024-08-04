package com.example.noteapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextFieldofNoteScreen(
    modifier: Modifier = Modifier,
    inputText: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardControl = LocalSoftwareKeyboardController.current
    TextField(
        value = inputText,
        onValueChange = onTextChange,
        maxLines = maxLine,
        label = { Text(text = label , color = Color.Black) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            onImeAction()
            keyboardControl?.hide()
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent, unfocusedTextColor = Color.Black, focusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Black , unfocusedIndicatorColor = Color.Black
        ),
        modifier = modifier
    )
}

@Composable
fun SaveButton(modifier: Modifier = Modifier, text: String, onclick: () -> Unit, enabled: Boolean = true) {
    Button(
        onClick = onclick,
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Blue),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text , color = Color.White)
    }
}
