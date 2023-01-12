package com.github.tumusx.feature_equipment

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tumusx.common_design_system.R
import com.github.tumusx.feature_equipment.util.ImageUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageUtilTest {
    private val imageUtil = ImageUtils()
    private lateinit var instrumentationContext: Context
    private var imageBitmap: Drawable? = null

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        imageBitmap = AppCompatResources.getDrawable(instrumentationContext, R.drawable.img_error)
    }

    @Test
    fun whenCallEncodeImageReturnBase64StringNotEmpty() {
        val resultImage = imageBitmap?.let { imageUtil.encodeImage(it.toBitmap()) }
        assertEquals(
            true, resultImage?.isNotEmpty()
        )
    }

}