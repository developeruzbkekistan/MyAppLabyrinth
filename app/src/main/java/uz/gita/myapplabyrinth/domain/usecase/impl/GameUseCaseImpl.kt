package uz.gita.myapplabyrinth.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.myapplabyrinth.domain.repository.AppRepository
import uz.gita.myapplabyrinth.domain.usecase.GameUseCase
import javax.inject.Inject
import javax.inject.Singleton

class GameUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : GameUseCase {

    override fun getMapByLevel(level: Int): Flow<Array<Array<Int>>> = flow {
        emit(repository.getMapByLevel(level))
    }.flowOn(Dispatchers.IO)
}