package uz.gita.myapplabyrinth.presentation.viewmodels

import androidx.lifecycle.LiveData

interface LevelViewModel {
    val openNextScreenLiveData: LiveData<Int>

    fun openNextScreenByLevel(level: Int)
}