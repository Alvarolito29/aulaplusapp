package com.example.prueba.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.data.model.SchoolClass
import com.example.prueba.data.repository.SchoolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val _schedule = MutableStateFlow<List<SchoolClass>>(emptyList())
    private val _filteredSchedule = MutableStateFlow<List<SchoolClass>>(emptyList())
    val filteredSchedule: StateFlow<List<SchoolClass>> = _filteredSchedule.asStateFlow()

    // Próxima clase destacada (simulada como la primera de la lista general)
    private val _nextClass = MutableStateFlow<SchoolClass?>(null)
    val nextClass: StateFlow<SchoolClass?> = _nextClass.asStateFlow()

    init {
        loadSchedule()
    }

    private fun loadSchedule() {
        viewModelScope.launch {
            val list = SchoolRepository.getSchedule()
            _schedule.value = list
            _filteredSchedule.value = list
            
            // Asumimos que la "próxima clase" es la primera de la lista por ahora
            if (list.isNotEmpty()) {
                _nextClass.value = list[0]
            }
        }
    }

    fun filterSchedule(query: String) {
        val currentList = _schedule.value
        if (query.isEmpty()) {
            _filteredSchedule.value = currentList
        } else {
            _filteredSchedule.value = currentList.filter { 
                it.subject.contains(query, ignoreCase = true) || 
                it.professor.contains(query, ignoreCase = true)
            }
        }
    }
}