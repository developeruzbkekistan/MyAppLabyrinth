package uz.gita.myapplabyrinth.presentation.viewmodels

import androidx.lifecycle.LiveData

interface GameViewModel {
    val mapByLevelLiveData : LiveData<Array<Array<Int>>>

    fun loadMapByLevel(level : Int)
}