package com.ilyaden.wallpaperapplication.ui.CategoriesScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyaden.wallpaperapplication.domain.UseCases.GetImageUseCase
import com.ilyaden.wallpaperapplication.data.Link
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel (
    val getImageUseCase: GetImageUseCase
    ) : ViewModel() {

    private val _images = mutableStateListOf<Link>()
    val images = _images

    fun getImageEvent(name :List<String>) {

        viewModelScope.launch(Dispatchers.IO) {
            images.addAll(getImageUseCase(name))
        }
    }

}