package team.ppac.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.foundation.FarmemeRadius

@Composable
internal fun RegisterInputArea(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RegisterListHeader(title = "밈의 제목")
        Spacer(modifier = Modifier.height(12.dp))
        Spacer(// TextField
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .padding(horizontal = 20.dp)
                .clip(FarmemeRadius.Radius10.shape)
                .background(
                    FarmemeTheme.backgroundColor.assistive,
                )
        )
        Spacer(modifier = Modifier.height(40.dp))
        RegisterListHeader(title = "밈의 출처")
        Spacer(modifier = Modifier.height(12.dp))
        Spacer( // TextField
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .padding(horizontal = 20.dp)
                .clip(FarmemeRadius.Radius10.shape)
                .background(
                    FarmemeTheme.backgroundColor.assistive,
                )
        )
        Spacer(modifier = Modifier.height(35.dp))
    }
}