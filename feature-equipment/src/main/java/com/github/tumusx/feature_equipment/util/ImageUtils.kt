package com.github.tumusx.feature_equipment.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.github.tumusx.feature_equipment.R
import java.io.ByteArrayOutputStream

fun encodeImage(bitmapImage: Bitmap): String? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun decodeImage(base64String: String, context: Context): Bitmap? {
    return try {
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }catch (illegalException: IllegalArgumentException) {
        illegalException.printStackTrace()
        val drawableImage = com.google.android.material.R.drawable.mtrl_ic_error
        AppCompatResources.getDrawable(context, drawableImage)?.toBitmap()
    }
}