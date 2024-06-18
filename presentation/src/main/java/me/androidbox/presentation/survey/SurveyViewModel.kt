package me.androidbox.presentation.survey

import androidx.lifecycle.ViewModel
import timber.log.Timber

class SurveyViewModel : ViewModel() {

    init {
        Timber.d("init ${SurveyViewModel::class.simpleName}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("OnCleared ${SurveyViewModel::class.simpleName}")
    }
}