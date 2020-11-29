package com.example.basicphotoeditor.domain

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.set
import com.squareup.picasso.Transformation

class FilterTransformation(private val filterType: Filter) : Transformation {

    enum class Filter {
        NONE, SEPIA, GREY, SKETCH
    }

    override fun transform(source: Bitmap): Bitmap {
        if(filterType == Filter.NONE)
            return source

        for (i in 0 until source.height) {
            for (j in 0 until source.height) {
                val pixel = source.getPixel(i, j)
                val red = Color.red(pixel)
                val green = Color.green(pixel)
                val blue = Color.blue(pixel)
                val alpha = Color.alpha(pixel)

                val newPixel = when(filterType) {
                    Filter.SEPIA -> toSepia(red, green, blue, alpha)
                    Filter.GREY -> toGrey(red, green, blue, alpha)
                    //Todo implement blur
                    else -> toSketch(red, green, blue, alpha)
                }
                source[i, j] = newPixel
            }
        }
        return source
    }


    private fun toGrey(red: Int, green: Int, blue: Int, alpha: Int): Int {
        val intensity: Int = (red + blue + green) / 3
        return Color.argb(alpha, intensity, intensity, intensity)
    }

    private fun toSepia(red: Int, green: Int, blue: Int, alpha: Int): Int {
        val newRed: Int = minOf((red * 0.393 + green * 0.769 + blue * 0.189).toInt(), 255)
        val newGreen: Int = minOf((red * 0.349 + green * 0.686 + blue * 0.168).toInt(), 255)
        val newBlue: Int = minOf((red * 0.272 + green * 0.534 + blue * 0.131).toInt(), 255)
        return Color.argb(alpha, newRed, newGreen, newBlue)
    }

    private fun toSketch(red: Int, green: Int, blue: Int, alpha: Int): Int {
        val intensity: Int = (red + blue + green) / 3
        // 120 is Intensity factor
        return when {
           intensity > 120 -> Color.argb(alpha, 255, 255, 255)
           intensity > 100 -> Color.argb(alpha, 150, 150, 150)
            else -> Color.argb(alpha, 0, 0, 0)
        }
    }


    override fun key(): String {
        return filterType.name;
    }
}