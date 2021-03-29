package me.heizi.log_machine.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.heizi.log_machine.persistence.entities.Project
import me.heizi.log_machine.repositories.ProjectionRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projects:ProjectionRepository
) : ViewModel () {
    val allProjects get() = _allProject.asStateFlow()
    private val _allProject:MutableStateFlow<List<Project>> = MutableStateFlow(emptyList())
    init {
        viewModelScope.launch(Default) {
            projects.all.flowOn(coroutineContext).collectLatest(_allProject::emit)
        }
    }
}