package base.ryuunta.base_mvvm.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import base.ryuunta.base_mvvm.R
import java.util.Random
import java.util.UUID


fun randomId(): String {
    Thread.sleep(1)     //delay to avoid duplicate timestamp
    //add timestamp in id for sorted
    return "${System.currentTimeMillis()}+${UUID.randomUUID()}"       //uuid form: timestamp.uuid
}

fun Context.convertDpToPixel(dp: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics

    val px = dp * (metrics.densityDpi / 160f)
    return px
}

fun Context.convertPixelsToDp(px: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics
    val dp = px / (metrics.densityDpi / 160f)
    return dp
}

fun <K, V> splitHashMap(map: Map<K, V>, chunkSize: Int): List<Map<K, V>> {
    val list = map.toList()
    val subLists = list.chunked(chunkSize)
    return subLists.map { it.toMap() }
}

fun getRandomSticker(context: Context, assetPath: String, iv: ImageView) {
    RLog.d("getRandomSticker", "load random sticker")
    val assetManager = context.assets
    try {
        val files = assetManager.list(assetPath)
        if (!files.isNullOrEmpty()) {
            val randomAssetName = Random().nextInt(files.size)
            val randomFile = files[randomAssetName]

            val inputStream = context.assets.open("$assetPath/$randomFile")
            val drawable = Drawable.createFromStream(inputStream, null)
            iv.setImageDrawable(drawable)
            inputStream.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getAssetBackground(context: Context, assetName: String, iv: ImageView) {
    RLog.d("getAssetBackground", "load specific background")
    try {
        val inputStream = context.assets.open("background/$assetName")
        val drawable = Drawable.createFromStream(inputStream, null)
        iv.setImageDrawable(drawable)
        inputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()

    }
}

fun getRandomColor(): Int {
    val listColor = listOf(
        R.color.pastel_blue,
        R.color.pastel_pink,
        R.color.pastel_blue_sea,
        R.color.pastel_lavender_blue,
//        R.color.pastel_pink_light,
        R.color.pastel_green_mint,
//        R.color.pastel_blue_sea_light,
//        R.color.pastel_blue_sky_light,
        R.color.pastel_lavender_purple,
//        R.color.pastel_green_mint_light,
        R.color.pastel_orange_1,
        R.color.pastel_orange_2,
        R.color.pastel_peach_1,
        R.color.pastel_peach_2,
        R.color.pastel_green_1,
        R.color.pastel_green_2,
    )
    return listColor[Random().nextInt(listColor.size)]

}