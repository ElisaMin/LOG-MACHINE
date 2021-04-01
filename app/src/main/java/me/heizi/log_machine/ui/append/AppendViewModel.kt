package me.heizi.log_machine.ui.append

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import me.heizi.log_machine.persistence.entities.Project
import me.heizi.log_machine.repositories.ProjectionRepository
import javax.inject.Inject

@HiltViewModel
class AppendViewModel @Inject constructor(
    private val repository: ProjectionRepository
) : ViewModel() {

    val title = MutableStateFlow("")
    val description = MutableStateFlow("")
    private fun checkIsAlready():Boolean =
        title.value.trim().isNotEmpty() && description.value.trim().isNotEmpty()
    suspend fun save():Boolean {
        Log.d(TAG, "save: called")
        require(checkIsAlready()) {IllegalArgumentException("空")}
        Log.d(TAG, "save: passed unless")
        return repository.runCatching {
            Log.d(TAG, "save: catching")
            withContext(IO) {
                Project(name = title.value,description = description.value,rank = 0.0)
                    .let(repository::resign)
                Log.d(TAG, "save: return")
            }
            Log.d(TAG, "save: job dying")
        }.onFailure {
            Log.d(TAG, "save: error",it)
        }.isSuccess
    }
}