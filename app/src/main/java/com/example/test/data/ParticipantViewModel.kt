package com.example.test.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParticipantViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Participant>>
    private val repository: ParticipantRepository
    init{
        val participantDao = ParticipantDatabase.getDatabase(application).participantDao()
        repository = ParticipantRepository(participantDao)
        readAllData = repository.readAllData
    }

    fun addParticipant(participant: Participant){
        viewModelScope.launch(Dispatchers.IO){
            repository.addParticipant(participant)
        }
    }
}

