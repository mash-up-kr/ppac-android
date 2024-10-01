package team.ppac.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.textfield.FarmemeTextArea
import team.ppac.designsystem.component.textfield.FarmemeTextField

private const val MAX_TITLE_LENGTH = 18
private const val MAX_SOURCE_LENGTH = 32

@Composable
internal fun RegisterInputArea(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChanged: (String) -> Unit,
    source: String,
    onSourceChanged: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RegisterListHeader(title = "밈의 제목")
        Spacer(modifier = Modifier.height(12.dp))
        FarmemeTextField(
            text = title,
            onTextChanged = {
                if (it.length <= MAX_TITLE_LENGTH) {
                    onTitleChanged(it)
                }
            },
            trailingContent = {
                TrailingTextLength(
                    textCount = title.length,
                    maxCount = MAX_TITLE_LENGTH,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        RegisterListHeader(title = "밈의 출처")
        Spacer(modifier = Modifier.height(12.dp))
        FarmemeTextArea(
            text = source,
            onTextChanged = {
                if (it.length <= MAX_SOURCE_LENGTH) {
                    onSourceChanged(it)
                }
            },
            trailingContent = {
                TrailingTextLength(
                    textCount = source.length,
                    maxCount = MAX_SOURCE_LENGTH,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(35.dp))
    }
}

@Composable
fun TrailingTextLength(
    textCount: Int,
    maxCount: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = textCount.toString(),
            style = FarmemeTheme.typography.body.medium.medium,
            color = when (textCount) {
                0 -> FarmemeTheme.textColor.assistive
                maxCount -> FarmemeTheme.textColor.brand
                else -> FarmemeTheme.textColor.secondary
            }
        )
        Text(
            text = "/",
            style = FarmemeTheme.typography.body.medium.medium,
            color = FarmemeTheme.textColor.assistive
        )
        Text(
            text = maxCount.toString(),
            style = FarmemeTheme.typography.body.medium.medium,
            color = FarmemeTheme.textColor.assistive
        )
    }
}