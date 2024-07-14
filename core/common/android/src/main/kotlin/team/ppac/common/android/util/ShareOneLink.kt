package team.ppac.common.android.util

import android.content.Context
import android.content.Intent

fun Context.shareOneLink(memeId: String){
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, FARMEME_ONE_LINK.plus("?$DEEP_LINK_VALUE=$memeId"))
    startActivity(Intent.createChooser(intent, "System UI에 표시할 문구"))
}
private const val DEEP_LINK_VALUE = "deep_link_value"
private const val FARMEME_ONE_LINK = "https://farmeme.onelink.me/RtpU/y09dosru"