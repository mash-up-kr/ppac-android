package team.ppac.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import team.ppac.common.android.extension.forceKillApplication
import team.ppac.designsystem.FarmemeTheme
import team.ppac.navigator.MainNavigator
import team.ppac.splash.mvi.SplashIntent
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

                        SplashSideEffect.ForceFinish -> {
                            this@SplashActivity.forceKillApplication()
                        }
                    }
                }
            }

            val state by viewModel.state.collectAsState()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = team.ppac.designsystem.R.drawable.img_splash_logo),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "내가 찾던 밈을 파밍하다",
                    style = FarmemeTheme.typography.body.large.medium,
                    color = FarmemeTheme.textColor.primary,
                )
            }
            if (state.isNetworkError) {
                NetworkErrorDialog(
                    onConfirmClick = {
                        viewModel.intent(SplashIntent.ClickDialogConfirm)
                    },
                )
            }
        }
    }
}