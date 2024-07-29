package team.ppac.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar

@Composable
internal fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    FarmemeScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            FarmemeBackToolBar(
                title = "개인정보 처리방침",
                onClickBackIcon = navigateToBack,
            )
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(FarmemeTheme.backgroundColor.assistive),
            )
            Spacer(
                // 웹뷰 추가
                modifier = Modifier
                    .fillMaxSize()
                    .background(FarmemeTheme.backgroundColor.brand),
            )
        }
    }
}