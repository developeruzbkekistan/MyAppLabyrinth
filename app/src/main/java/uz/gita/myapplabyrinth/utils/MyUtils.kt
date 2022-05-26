package uz.gita.myapplabyrinth.utils

import android.util.Log

fun <T> T.scope(block : T.() -> Unit) {
    block(this)
}


fun myLog(message : String, tag : String = "TTT") {
    Log.d(tag, message)
}