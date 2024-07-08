package team.ppac.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.designsystem.FarmemeTheme

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmemeTheme {
                DetailRoute()
            }
        }
    }
}