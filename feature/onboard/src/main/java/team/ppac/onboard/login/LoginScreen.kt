package team.ppac.onboard.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest
import team.ppac.onboard.login.mvi.LoginIntent
import team.ppac.onboard.login.mvi.LoginSideEffect

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest {
            when(it){
                LoginSideEffect.Toast -> Toast.makeText(context, "", Toast.LENGTH_LONG).show()
                LoginSideEffect.ScrollToBottom -> scrollState.scrollToItem(10)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("임시로 만든 로그인 화면")
        Button(
            onClick = {
                viewModel.intent(LoginIntent.ClickLoginButton)
            },
        ){
            Text(state.nickname)
        }
    }

    LazyColumn(state = scrollState) {
        item {

        }
    }
}
