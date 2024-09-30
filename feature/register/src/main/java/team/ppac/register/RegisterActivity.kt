package team.ppac.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.common.android.util.noTransitionAnimation
import team.ppac.designsystem.FarmemeTheme

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FarmemeTheme {
                RegisterRoute(
                    navigateToBack = { finish() }
                )
            }
        }
        noTransitionAnimation()
    }
}