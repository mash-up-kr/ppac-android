package team.ppac.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.R
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar
import team.ppac.setting.component.SettingListItem

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(),
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            item {
                FarmemeBackToolBar(
                    title = "설정",
                    onClickBackIcon = { }, // TODO : onClick 적용
                )
            }
            item {
                SettingBody()
            }
            item {
                SettingListItem(
                    title = "개인정보 처리방침",
                    onClick = { }, // TODO : onClick 적용
                )
            }
        }
    }
}

@Composable
private fun SettingBody(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(FarmemeTheme.backgroundColor.assistive),
        )
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            modifier = Modifier.size(70.dp),
            painter = painterResource(R.drawable.img_farmeme_logo),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "파밈",
            color = FarmemeTheme.textColor.primary,
            style = FarmemeTheme.typography.heading.medium.semibold,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "v.1.0.0", // TODO : appVersion 적용
            color = FarmemeTheme.textColor.tertiary,
            style = FarmemeTheme.typography.body.small.medium,
        )
        Spacer(modifier = Modifier.height(50.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(1.dp)
                .background(FarmemeTheme.backgroundColor.assistive),
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen()
}