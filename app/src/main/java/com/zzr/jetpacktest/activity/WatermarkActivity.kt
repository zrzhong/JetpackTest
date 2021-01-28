package com.zzr.jetpacktest.activity

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.watermark.androidwm_light.WatermarkBuilder
import com.watermark.androidwm_light.bean.WatermarkText
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.sputils.ImageUtil


class WatermarkActivity : AppCompatActivity(R.layout.activity_watermark) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_watermark)
        val ivWatermark = findViewById<ImageView>(R.id.ivWatermark)
        val ss = "2020-10-20 星期五 何小明"
        val ss2 = "何小明"
        val watermarkText: WatermarkText = WatermarkText(ss)
            .setPositionX(0.5)
            .setPositionY(0.93)
            .setTextColor(Color.WHITE)
//            .setTextFont(R.font.champagne)
//            .setTextShadow(0.1f, 5f, 5f, Color.BLUE)
            .setTextAlpha(255)
//            .setRotation(30.0)
            .setTextSize(12.0)

        val watermarkText2: WatermarkText = WatermarkText(ss2)
            .setPositionX(0.5)
            .setPositionY(0.8)
            .setTextColor(Color.WHITE)
//            .setTextFont(R.font.champagne)
//            .setTextShadow(0.1f, 5f, 5f, Color.BLUE)
            .setTextAlpha(255)
//            .setRotation(30.0)
            .setTextSize(12.0)

        val watermarkTexts = mutableListOf(watermarkText, watermarkText2)
        val bitmap2 = WatermarkBuilder
            .create(this, R.drawable.pic001)
            .loadWatermarkText(watermarkText) // use .loadWatermarkImage(watermarkImage) to load an image.
            //            .loadWatermarkTexts(watermarkTexts)
            .watermark
            .outputImage
//            .setToImageView(ivWatermark)
        ivWatermark.setImageBitmap(bitmap2)

//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic001)
//        val newBitmap = ImageUtil.drawTextToCenter2(this, bitmap, ss, 12, R.color.color_fff, 20)
//        ivWatermark.setImageBitmap(newBitmap)
    }
}