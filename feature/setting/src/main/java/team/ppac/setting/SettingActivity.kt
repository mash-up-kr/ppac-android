package team.ppac.setting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.common.android.util.noTransitionAnimation
import team.ppac.common.android.util.onBackPressed
import team.ppac.designsystem.FarmemeTheme

@AndroidEntryPoint
class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FarmemeTheme {
                val navController = rememberNavController()

                SettingNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    navigateToBack = {
                        finish()
                        noTransitionAnimation()
                    },
                )
            }
        }

        onBackPressed {
            finish()
            noTransitionAnimation()
        }
    }
}