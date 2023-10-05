package com.ilyaden.wallpaperapplication.domain.UseCases

import com.ilyaden.wallpaperapplication.tools.Link
import com.ilyaden.wallpaperapplication.tools.ParseSite.parse

class GetImagesUseCase {

    suspend operator fun invoke(name: String): ArrayList<Link> {
        return parse(name)
    }
}
