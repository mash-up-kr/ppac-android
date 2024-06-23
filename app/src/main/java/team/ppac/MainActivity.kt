package team.ppac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.designsystem.component.scaffold.FarmemeScaffold
import team.ppac.designsystem.foundation.FarmemeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmemeTheme {
                
            }
        }
    }
}