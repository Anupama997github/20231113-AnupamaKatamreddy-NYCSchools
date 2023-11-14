package com.nycschools.mvvm.model

import com.nycschools.room.NycSchool

sealed class NycSchoolData {
    data class ListOfSchools(val listOfSchools: List<NycSchool>?) : NycSchoolData()
    data class Failure(val errorCode: String) : NycSchoolData()
    data class Loading(var loading: Boolean) : NycSchoolData()

}
