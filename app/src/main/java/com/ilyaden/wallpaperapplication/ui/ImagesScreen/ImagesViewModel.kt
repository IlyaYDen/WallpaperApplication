package com.ilyaden.wallpaperapplication.ui.ImagesScreen

import android.app.WallpaperManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ilyaden.wallpaperapplication.domain.UseCases.GetImagesUseCase
import com.ilyaden.wallpaperapplication.tools.Link
import com.ilyaden.wallpaperapplication.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Double.min
import java.net.URL


class ImagesViewModel(
    val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val _images = mutableStateListOf<Link>()
    val images = _images

    fun getImagesEvent(name :String) {

        viewModelScope.launch(Dispatchers.IO) {
            _images.addAll(getImagesUseCase(name))
        }
    }



    fun setWallpaper(mainActivity: MainActivity, lnk: String, wallpaperFlag: Int, width: Int, height :Int) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val wallpaperManager = WallpaperManager.getInstance(mainActivity)

                // Download the image from the URL
                val inputStream = URL(lnk).openStream()
                val originalBitmap = BitmapFactory.decodeStream(inputStream)


                val metrics = Resources.getSystem().displayMetrics
                val screenHeight = metrics.heightPixels
                val screenWidth = metrics.widthPixels

                val wallpaperBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)

                val canvas = Canvas(wallpaperBitmap)

                val scalingFactor = screenHeight.toDouble() / originalBitmap.height
                val newImageWidth = (originalBitmap.width * scalingFactor).toInt()
                val newImageHeight = (originalBitmap.height * scalingFactor).toInt()

                val startX = (screenWidth - newImageWidth) / 2
                val startY = (screenHeight - newImageHeight) / 2

                canvas.drawColor(Color.BLACK)
                canvas.drawBitmap(
                    Bitmap.createScaledBitmap(originalBitmap, newImageWidth, newImageHeight, true),
                    startX.toFloat(),
                    startY.toFloat(),
                    null
                )


                wallpaperManager.setBitmap(wallpaperBitmap,
                    null,
                    true,
                    wallpaperFlag
                )

//Rect

                // Close the input stream
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun returnBitmap(originalImage: Bitmap, width: Int, height: Int): Bitmap? {
        val background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val originalWidth = originalImage.width.toFloat()
        val originalHeight = originalImage.height.toFloat()
        val canvas = Canvas(background)
        val scale = width / originalWidth
        val xTranslation = 0.0f
        val yTranslation = (height - originalHeight * scale) / 2.0f
        val transformation = Matrix()
        transformation.postTranslate(xTranslation, yTranslation)
        transformation.preScale(scale, scale)
        val paint = Paint()
        paint.setFilterBitmap(true)
        canvas.drawBitmap(originalImage, transformation, paint)
        return background
    }

}

class ImagesViewModelFactory constructor(

    val getImagesUseCase: GetImagesUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(
            getImagesUseCase
        ) as T

    }
}