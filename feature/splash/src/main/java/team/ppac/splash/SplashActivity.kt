package team.ppac.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.navigator.MainNavigator
import team.ppac.splash.mvi.SplashSideEffect
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject
    lateinit var mainNavigator: MainNavigator

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LaunchedEffect(viewModel) {
                viewModel.sideEffect.collect {
                    when (it) {
                        SplashSideEffect.NavigateToMain -> {
                            mainNavigator.navigateFrom(
                                activity = this@SplashActivity,
                                withFinish = true
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = team.ppac.designsystem.R.drawable.img_splash_logo),
                    contentDescription = null,
                )
            }
        }
    }
}