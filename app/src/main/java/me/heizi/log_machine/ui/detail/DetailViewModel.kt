package me.heizi.log_machine.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.repositories.LogsRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val logsRepository: LogsRepository
) : ViewModel() {
    val logs get() = _logs!!
    val inputText = MutableStateFlow("")
    private var _logs:StateFlow<List<Log>>? = null

    suspend fun start(id:Int) {
        _logs = logsRepository.getLogsByID(id).stateIn(viewModelScope)
    }
    fun updateLogTitle(logId:Int,msg:String) = viewModelScope.launch(IO) {
        logsRepository.updateLogTitle(logId,msg)
    }
    suspend fun saveLog(log: Log):Boolean {
        return logsRepository.insert(log) == 1
    }


}