package team.ppac.setting.policy

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import team.ppac.designsystem.FarmemeTheme
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.component.toolbar.FarmemeBackToolBar

const val PRIVACY_POLICY_URL =
    "https://snow-chestnut-45b.notion.site/03c44635666546718a4540874f824cd7"

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
                onBackIconClick = navigateToBack,
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
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                    }
                },
                update = { webView ->
                    webView.loadUrl(PRIVACY_POLICY_URL)
                },
            )
        }
    }
}