package team.ppac.errorhandling

import java.lang.Exception

class FarmemeException(
    val code: Int,
    override val message: String?,
) : Exception(message)

fun throwFarmemeException(
    code: Int,
    message: String
): Nothing {
    throw FarmemeException(
        code = code,
        message = message
    )
}