package team.ppac.setting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import team.ppac.designsystem.FarmemeTheme

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmemeTheme {
                SettingScreen(
                    onClickBackButton = {
                        finish()
                    },
                )
            }
        }
    }
}