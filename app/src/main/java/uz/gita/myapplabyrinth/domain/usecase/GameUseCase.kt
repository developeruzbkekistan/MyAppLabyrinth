package uz.gita.myapplabyrinth.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getMapByLevel(level : Int) : Flow<Array<Array<Int>>>
}