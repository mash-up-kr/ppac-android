package team.ppac.onboard.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.ppac.onboard.login.mvi.LoginIntent

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("임시로 만든 로그인 화면")
        Button(
            onClick = {
                viewModel.intent(LoginIntent.ClickLoginButton)
            },
        ){
            Text("로그인")
        }
    }
}