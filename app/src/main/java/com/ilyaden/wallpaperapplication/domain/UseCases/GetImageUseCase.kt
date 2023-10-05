package com.ilyaden.wallpaperapplication.domain.UseCases

import com.ilyaden.wallpaperapplication.tools.Link
import com.ilyaden.wallpaperapplication.tools.ParseSite.parse

class GetImageUseCase {

    suspend operator fun invoke(name: List<String>): List<Link> {
        val list = mutableListOf<Link>()
        name.forEach {
            list.add(parse(it,1)[0])
        }
        return list
    }
}
