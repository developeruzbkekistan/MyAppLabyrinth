package uz.gita.myapplabyrinth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.myapplabyrinth.domain.usecase.GameUseCase
import uz.gita.myapplabyrinth.domain.usecase.SplashUseCase
import uz.gita.myapplabyrinth.domain.usecase.impl.GameUseCaseImpl
import uz.gita.myapplabyrinth.domain.usecase.impl.SplashUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun getGameUseCase(impl: GameUseCaseImpl): GameUseCase

    @Binds
    fun getSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase
}


