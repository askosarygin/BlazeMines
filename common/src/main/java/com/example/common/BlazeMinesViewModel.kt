package com.example.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class BlazeMinesViewModel <MODEL>(
    model: MODEL
) : ViewModel() {
    private val _model = MutableStateFlow(model)
    val model: StateFlow<MODEL> = _model

    protected fun update(function: (MODEL) -> MODEL) {
        _model.update {
            function(it)
        }
    }
}