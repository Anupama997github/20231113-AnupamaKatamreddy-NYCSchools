package com.nycschools.mvvm.repository

import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.room.NycSchool

interface NycSchoolsRepository {
    suspend fun getNetworkSchools(forceRefresh: Boolean = false): NycSchoolData

    suspend fun insertHighSchoolRecordsInDB(listOFRecord: List<NycSchool>)

    suspend fun getAllRecords(): List<NycSchool>

    suspend fun updateSatScores()

    suspend fun updateNycSchoolRecords(listOfRecords: List<NycSchool>?)

    suspend fun getNycSchoolRecord(id: String): NycSchool

    suspend fun clearAllTheRecords()

}