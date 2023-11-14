package com.nycschools.mvvm.usecase

import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.mvvm.repository.NycSchoolsRepository
import com.nycschools.room.NycSchool
import javax.inject.Inject
class ListSchoolsUsecase @Inject constructor(val nycSchoolsRepository: NycSchoolsRepository) {

    suspend fun getListOfSchools(): NycSchoolData = nycSchoolsRepository.getNetworkSchools()

    suspend fun getNycSchool(id:String): NycSchool {
        val nycSchool = nycSchoolsRepository.getNycSchoolRecord(id)
        return nycSchool
    }

}