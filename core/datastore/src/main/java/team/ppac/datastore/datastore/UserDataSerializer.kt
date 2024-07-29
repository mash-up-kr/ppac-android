package team.ppac.datastore.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import team.ppac.core.datastore.UserData
import java.io.InputStream
import java.io.OutputStream

internal object UserDataSerializer : Serializer<UserData> {

    override val defaultValue: UserData = UserData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserData {
        return withContext(Dispatchers.IO) {
            try {
                if (input.available() != 0) {
                    UserData.parseFrom(input)
                } else {
                    defaultValue
                }
            } catch (e: SerializationException) {
                throw CorruptionException("Unable to read UserPrefs", e)
            }
        }
    }

    override suspend fun writeTo(t: UserData, output: OutputStream) {
        return withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }
}
