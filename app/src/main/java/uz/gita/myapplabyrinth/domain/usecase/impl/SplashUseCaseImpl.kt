package uz.gita.myapplabyrinth.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.myapplabyrinth.domain.repository.AppRepository
import uz.gita.myapplabyrinth.domain.usecase.SplashUseCase
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : SplashUseCase {
    override fun loadMap(): Flow<Unit> = flow {
        repository.loadMap()
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}