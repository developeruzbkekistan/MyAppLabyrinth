package uz.gita.myapplabyrinth.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SplashUseCase {
    fun loadMap() : Flow<Unit>
}