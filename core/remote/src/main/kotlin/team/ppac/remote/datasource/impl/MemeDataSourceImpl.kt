package team.ppac.remote.datasource.impl

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import team.ppac.remote.api.MemeApi
import team.ppac.remote.datasource.MemeDataSource
import team.ppac.remote.model.response.meme.MemeResponse
import team.ppac.remote.model.response.user.SavedMemesResponse
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class MemeDataSourceImpl @Inject constructor(
    private val memeApi: MemeApi,
    @ApplicationContext private val context: Context,
) : MemeDataSource {
    override suspend fun getMemeById(memeId: String): MemeResponse {
        return memeApi.getMemeById(memeId = memeId)
    }

    override suspend fun getRecommendMemes(): List<MemeResponse> {
        return memeApi.getRecommendMemes()
    }

    override suspend fun saveMeme(memeId: String): Boolean {
        return memeApi.saveMeme(memeId)
    }

    override suspend fun deleteSavedMeme(memeId: String): Boolean {
        return memeApi.deleteSavedMeme(memeId)
    }

    override suspend fun getSearchMemes(keyword: String, page: Int, size: Int): SavedMemesResponse {
        return memeApi.getSearchMemes(keyword, page, size)
    }

    override suspend fun reactMeme(memeId: String): Boolean {
        return memeApi.reactMeme(memeId)
    }

    override suspend fun watchMeme(memeId: String, type: String): Boolean {
        return memeApi.watchMeme(memeId, type)
    }

    override suspend fun uploadMeme(
        keywordIds: List<String>,
        memeImageUri: String,
        memeTitle: String,
        memeSource: String
    ): Boolean {
        val file = getFileFromUri(memeImageUri.toUri()) ?: return false
        val imageBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", file.name, imageBody)

        val keywords = keywordIds.map { MultipartBody.Part.createFormData("keywordIds", it) }
        val title = MultipartBody.Part.createFormData("title", memeTitle)
        val source = MultipartBody.Part.createFormData("source", memeSource)
        return memeApi.postMeme(
            title = title,
            image = imagePart,
            source = source,
            keywordIds = keywords
        )
    }

    private fun getFileFromUri(uri: Uri): File? {
        val contentResolver: ContentResolver = context.contentResolver
        val fileName = getFileName(contentResolver, uri) ?: return null // 파일 이름 얻기
        val tempFile = File(context.cacheDir, fileName)

        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val outputStream: OutputStream = FileOutputStream(tempFile)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return tempFile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getFileName(contentResolver: ContentResolver, uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex("_display_name")
                it.getString(index)
            } else null
        }
    }
}
