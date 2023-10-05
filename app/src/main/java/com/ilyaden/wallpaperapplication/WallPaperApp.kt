package com.ilyaden.wallpaperapplication

import android.app.Application
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowMetricsCalculator
import com.ilyaden.wallpaperapplication.di.DIContainer
import com.ilyaden.wallpaperapplication.domain.UseCases.GetImageUseCase
import com.ilyaden.wallpaperapplication.domain.UseCases.GetImagesUseCase
import com.ilyaden.wallpaperapplication.ui.CategoriesScreen.CategoriesViewModel
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesViewModel
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesViewModelFactory

class WallPaperApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        DIContainer.registerDependency(connectivityManager)

        DIContainer.registerDependency(ImagesViewModel(GetImagesUseCase()))
        DIContainer.registerDependency(CategoriesViewModel(GetImageUseCase()))

    }
}