package com.kelvin.githubappapi.usecase

abstract class BaseUseCase<in Params, out T> {
    abstract fun execute(params: Params): T
}
