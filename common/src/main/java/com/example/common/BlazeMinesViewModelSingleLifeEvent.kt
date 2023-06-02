package com.example.common

import java.util.concurrent.atomic.AtomicBoolean

open class BlazeMinesViewModelSingleLifeEvent<EVENT>(
    private val event: EVENT
) : BlazeMinesViewModelEvent<EVENT>(event){
    private val processed = AtomicBoolean(false)

    override fun use(doEvent: (EVENT) -> Unit) {
        if (!processed.getAndSet(true)) {
            super.use(doEvent)
        }
    }
}