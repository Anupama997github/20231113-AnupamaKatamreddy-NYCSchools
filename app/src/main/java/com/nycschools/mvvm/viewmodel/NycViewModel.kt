package com.nycschools.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.mvvm.usecase.ListSchoolsUsecase
import com.nycschools.room.NycSchool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * View model is responsible will provide the timely updates to UX layer.
 */
@HiltViewModel
class NycViewModel @Inject constructor(val listSchoolsUsecase: ListSchoolsUsecase) : ViewModel() {

    private val _schoolData: MutableLiveData<NycSchoolData> = MutableLiveData()
    val schoolData: LiveData<NycSchoolData> = _schoolData

    private val _schoolInfo: MutableLiveData<NycSchool> = MutableLiveData()
    val schoolInfo: LiveData<NycSchool> = _schoolInfo

    fun getNycSchools() {
        viewModelScope.launch {
            _schoolData.postValue(listSchoolsUsecase.getListOfSchools())
        }
    }

    fun getNycSchoolInfo(id:String){
        viewModelScope.launch {
            _schoolInfo.postValue(listSchoolsUsecase.getNycSchool(id))
        }
    }
}