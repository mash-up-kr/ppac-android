package team.ppac.errorhandling

import java.lang.Exception


class FarmemeException(
    val code: Int,
    override val message: String?,
) : Exception()

