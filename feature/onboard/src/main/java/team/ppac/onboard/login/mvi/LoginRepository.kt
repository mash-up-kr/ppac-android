package team.ppac.onboard.login.mvi

import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    suspend fun getNames(): List<String>{
        delay(100L)
        return listOf("1","2")
    }
}