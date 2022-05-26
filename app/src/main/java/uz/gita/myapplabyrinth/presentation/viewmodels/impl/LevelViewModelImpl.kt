package uz.gita.myapplabyrinth.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myapplabyrinth.presentation.viewmodels.LevelViewModel
import javax.inject.Inject

@HiltViewModel
class LevelViewModelImpl @Inject constructor() : ViewModel(), LevelViewModel {
    override val openNextScreenLiveData = MutableLiveData<Int>()

    override fun openNextScreenByLevel(level: Int) {
        openNextScreenLiveData.value = level
    }
}