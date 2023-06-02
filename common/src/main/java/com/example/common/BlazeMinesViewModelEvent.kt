package com.example.common

open class BlazeMinesViewModelEvent <EVENT>(
    private val event: EVENT
) {

    open fun use(doEvent: (EVENT) -> Unit) {
        doEvent(event)
    }
}