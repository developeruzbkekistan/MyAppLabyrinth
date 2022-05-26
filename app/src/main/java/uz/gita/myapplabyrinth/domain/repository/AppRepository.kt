package uz.gita.myapplabyrinth.domain.repository

interface AppRepository {
    suspend fun loadMap()

    suspend fun getMapByLevel(level : Int) : Array<Array<Int>>
}