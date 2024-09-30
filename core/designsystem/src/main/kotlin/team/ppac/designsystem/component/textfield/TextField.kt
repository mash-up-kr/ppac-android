package team.ppac.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeIcon
import team.ppac.designsystem.foundation.FarmemeRadius
import team.ppac.designsystem.util.extension.noRippleClickable

@Composable
fun FarmemeSearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    BasicTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        textStyle = FarmemeTheme.typography.body.large.medium.copy(
            color = FarmemeTheme.textColor.primary
        ),
        enabled = enabled,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(FarmemeTheme.backgroundColor.brand),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(FarmemeRadius.Radius10.shape)
                    .background(
                        color = FarmemeTheme.backgroundColor.assistive,
                    )
                    .padding(vertical = 12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(16.dp))
                    FarmemeIcon.Search(
                        modifier = Modifier.size(20.dp),
                        tint = FarmemeTheme.iconColor.secondary
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    if (text.isBlank()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "찾고 싶은 밈 있어?",
                            style = FarmemeTheme.typography.body.large.medium.copy(
                                color = FarmemeTheme.textColor.tertiary
                            )
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(modifier = Modifier) {
                        FarmemeIcon.Delete(
                            modifier = Modifier
                                .size(20.dp)
                                .noRippleClickable(
                                    onClick = { onTextChanged("") }
                                ),
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    )
}

@Composable
fun FarmemeTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String = "",
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextChanged: (String) -> Unit,
    trailingContent: @Composable () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    BasicTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .height(46.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = FarmemeTheme.backgroundColor.assistive)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 14.dp,
                    ),
            ) {
                if (text.isBlank()) {
                    Text(
                        text = placeholder,
                        style = FarmemeTheme.typography.body.large.medium.copy(
                            color = FarmemeTheme.textColor.assistive
                        )
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Box(modifier = Modifier.align(Alignment.Bottom)) {
                        trailingContent()
                    }
                }
            }
        }
    )
}

@Composable
fun FarmemeTextArea(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String = "",
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    onTextChanged: (String) -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    BasicTextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .height(82.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = FarmemeTheme.backgroundColor.assistive)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 14.dp,
                    ),
            ) {
                if (text.isBlank()) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = placeholder,
                        style = FarmemeTheme.typography.body.large.medium.copy(
                            color = FarmemeTheme.textColor.assistive
                        )
                    )
                }
                Row(modifier = Modifier.matchParentSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    Box(modifier = Modifier.align(Alignment.Bottom)) {
                        trailingContent()
                    }
                }
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    var value by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        FarmemeSearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            onTextChanged = { value = it },
        )
        Spacer(modifier = Modifier.size(16.dp))
        FarmemeTextField(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            placeholder = "예) 러키비키잖아",
            onTextChanged = {
                value = it
            },
            trailingContent = {
                Text(
                    text = "${value.length} / 10",
                    style = FarmemeTheme.typography.body.large.medium.copy(
                        color = FarmemeTheme.textColor.assistive
                    )
                )
            }
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        FarmemeTextArea(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            onTextChanged = {
                value = it
            },
            placeholder = "예) 무한도전, 핀터레스트",
            trailingContent = {
                Text(
                    text = "${value.length} / 20",
                    style = FarmemeTheme.typography.body.large.medium.copy(
                        color = FarmemeTheme.textColor.assistive
                    )
                )
            }
        )
    }
}