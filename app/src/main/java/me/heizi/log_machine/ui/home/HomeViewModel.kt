package me.heizi.log_machine.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.heizi.log_machine.persistence.entities.Project
import me.heizi.log_machine.repositories.ProjectionRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projects:ProjectionRepository
) : ViewModel () {
    val allProjects: StateFlow<List<Project>> = MutableStateFlow(emptyList())


    suspend fun start() {
        val mutableStateFlow = allProjects as MutableStateFlow
        viewModelScope.launch(Default) {
            projects.all.collect(mutableStateFlow::emit)
        }
    }
}