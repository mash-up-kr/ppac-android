package team.ppac.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import team.ppac.local.entity.UserData
import java.io.InputStream
import java.io.OutputStream

internal object UserDataSerializer : Serializer<UserData> {

    override val defaultValue: UserData = UserData.EMPTY

    override suspend fun readFrom(input: InputStream): UserData {
        try {
            return Json.decodeFromString(
                UserData.serializer(), input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Unable to read UserPrefs", e)
        }
    }

    override suspend fun writeTo(t: UserData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(UserData.serializer(), t).encodeToByteArray()
            )
        }
    }
}
