package com.example.android.trackmysleepquality.sleepquality

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import kotlinx.coroutines.*

class SleepQualityViewModel(
        private val sleepNightKey: Long = 0L,
        val database: SleepDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToSleepTracker =  MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    fun onSetSleepQuality(quality: Int) {
        uiScope.launch {
            update(quality)
            _navigateToSleepTracker.value = true
        }
    }

    private suspend fun update(quality: Int) {
        withContext(Dispatchers.IO) {
            val tonight = database.getNight(sleepNightKey) ?: return@withContext
            tonight.sleepQuality = quality
            database.update(tonight)
        }
    }

    fun onNavigationCompleted() {
        _navigateToSleepTracker.value = false
    }
}

