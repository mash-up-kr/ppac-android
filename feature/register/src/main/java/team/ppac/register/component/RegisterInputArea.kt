package team.ppac.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.component.textfield.FarmemeTextField

@Composable
internal fun RegisterInputArea(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChanged: (String) -> Unit,
    source: String,
    onSourceChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RegisterListHeader(title = "밈의 제목")
        Spacer(modifier = Modifier.height(12.dp))
        FarmemeTextField(text = title, onTextChanged = onTitleChanged)
        Spacer(modifier = Modifier.height(40.dp))
        RegisterListHeader(title = "밈의 출처")
        Spacer(modifier = Modifier.height(12.dp))
        FarmemeTextField(text = source, onTextChanged = onSourceChanged)
        Spacer(modifier = Modifier.height(35.dp))
    }
}