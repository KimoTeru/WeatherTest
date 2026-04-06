package com.kimoteru.weathertest.core.common.base

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseLogic<S : BaseContract.UiState, E : BaseContract.UiEvent>(
    protected val coroutineScope: CoroutineScope
) : InstanceKeeper.Instance {

    abstract val uiState: StateFlow<S>

    abstract fun onEvent(event: E)

    override fun onDestroy() {
        coroutineScope.cancel()
    }
}