package team.ppac.common.android.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.core.content.FileProvider
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

/* bitmap을 ClipBoard에 copy하는 함수, Menifest에 FileProvider 정의 필요*/
fun Context.copyImageToClipBoard(bitmap: Bitmap) {
    runCatching {
        // 비트맵 File로 저장
        val imagesFolder = File(cacheDir, "images").apply {
            mkdir()
        }
        val imageFile = File(imagesFolder, "share_image.png")
        FileOutputStream(imageFile).run {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
            flush()
            close()
        }

        // uri 얻어오기
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            imageFile
        )

        // 클립보드에 이미지 Uri 복사
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newUri(contentResolver, "Image", uri)
        clipboard.setPrimaryClip(clip)
    }.onFailure {
        Toast.makeText(this, "기기의 저장공간을 확인해주세요!", Toast.LENGTH_LONG).show()
        Timber.e(it)
    }
}