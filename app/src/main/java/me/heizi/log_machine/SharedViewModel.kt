package me.heizi.log_machine

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Shared view model
 *
 * 主要功能是检测Activity什么的Destroy之类的
 */
class SharedViewModel @ViewModelInject constructor(
    val database: TheDatabase
) : ViewModel() {
    /** 清理时记录事件 */
    override fun onCleared() {
        super.onCleared()
        GlobalScope.launch(IO) {
            database.destroyingViewModel()
        }
    }
}