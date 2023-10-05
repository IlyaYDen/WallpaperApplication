package com.ilyaden.wallpaperapplication.di

object DIContainer {
    private val dependencyMap = mutableMapOf<Class<*>, Any>()

    fun registerDependency(dependency: Any) {
        dependencyMap[dependency.javaClass] = dependency
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> resolveDependency(clazz: Class<T>): T {
        return dependencyMap[clazz] as T
    }
}