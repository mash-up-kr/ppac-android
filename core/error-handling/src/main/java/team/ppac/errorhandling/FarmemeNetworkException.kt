package team.ppac.errorhandling

import team.ppac.errorhandling.FarmemeNetworkException.Companion.INTERNAL_SERVER_ERROR_CODE
import team.ppac.errorhandling.FarmemeNetworkException.Companion.INTERNAL_SERVER_ERROR_MESSAGE
import team.ppac.errorhandling.FarmemeNetworkException.Companion.UNKNOWN_ERROR
import team.ppac.errorhandling.FarmemeNetworkException.Companion.UNKNOWN_ERROR_MESSAGE
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class FarmemeNetworkException(
    code: Int,
    override val message: String?,
) : FarmemeException(code, message) {
    companion object {
        const val INTERNAL_SERVER_ERROR_CODE = 500
        const val INTERNAL_SERVER_ERROR_MESSAGE = "네트워크 연결을 확인할 수 없습니다. 잠시 후 다시 시도해주세요."

        const val UNKNOWN_ERROR = -9999
        const val UNKNOWN_ERROR_MESSAGE = "알수 없는 에러가 발생하였습니다."
    }
}

fun Exception.parseWithNetworkError(): FarmemeNetworkException {
    return when (this) {
        is UnknownHostException,
        is SocketException,
        is SocketTimeoutException,
        -> {
            FarmemeNetworkException(
                code = INTERNAL_SERVER_ERROR_CODE,
                message = INTERNAL_SERVER_ERROR_MESSAGE,
            )
        }

        else -> {
            FarmemeNetworkException(
                code = UNKNOWN_ERROR,
                message = UNKNOWN_ERROR_MESSAGE,
            )
        }
    }
}
