package team.ppac.onboard.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.navigator.SampleNavigator
import javax.inject.Inject

@AndroidEntryPoint
class OnboardActivity : ComponentActivity() {
    @Inject
    lateinit var sampleNavigator: SampleNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(viewModel = hiltViewModel()) {
                sampleNavigator.navigateFrom(this@OnboardActivity)
            }
        }
    }
}