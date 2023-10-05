package com.ilyaden.wallpaperapplication.domain.UseCases

import com.ilyaden.wallpaperapplication.data.Link
import com.ilyaden.wallpaperapplication.data.ParseSite.parse

class GetImagesUseCase {

    suspend operator fun invoke(name: String, page: Int): List<Link> {
        return parse(name, page = page)
    }
}
