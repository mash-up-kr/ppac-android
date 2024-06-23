package team.ppac.errorhandling

import java.io.IOException

open class FarmemeException(
    val code: Int,
    override val message: String?,
) : IOException(message)

fun throwFarmemeException(
    code: Int,
    message: String
): Nothing {
    throw FarmemeException(
        code = code,
        message = message
    )
}
