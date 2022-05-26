package uz.gita.myapplabyrinth.presentation.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.myapplabyrinth.domain.usecase.GameUseCase
import uz.gita.myapplabyrinth.presentation.viewmodels.GameViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val useCase: GameUseCase
) : ViewModel(), GameViewModel {
    override val mapByLevelLiveData= MutableLiveData<Array<Array<Int>>>()

    override fun loadMapByLevel(level: Int) {
        useCase.getMapByLevel(level).onEach {
            mapByLevelLiveData.value = it
        }.launchIn(viewModelScope)
    }
}

