package me.heizi.log_machine.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.heizi.log_machine.Application.Companion.TAG
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.repositories.LogsRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val logsRepository: LogsRepository
) : ViewModel() {
    val logs:StateFlow<List<Log>> = MutableStateFlow(emptyList())
    val inputText = MutableStateFlow("")

    suspend fun start(id:Int) {
        val logs = this.logs as MutableStateFlow
        logsRepository.getLogsByID(id).collect(logs::emit)
    }
    fun updateLogTitle(logId:Int,msg:String) = viewModelScope.launch(IO) {
        logsRepository.updateLogTitle(logId,msg)
    }
    suspend fun saveLog(log: Log):Boolean =logsRepository.runCatching {
        logsRepository.insert(log)
        android.util.Log.d(TAG, "saveLog: dying")
    }.onFailure {
        android.util.Log.d(TAG, "saveLog: error",it)
    }.isSuccess


}