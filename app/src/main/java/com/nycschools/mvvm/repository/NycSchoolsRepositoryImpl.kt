package com.nycschools.mvvm.repository

import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.network.NycSchoolApiService
import com.nycschools.network.ResponseStatus
import com.nycschools.network.Status
import com.nycschools.room.NycSchool
import com.nycschools.room.NycSchoolDao
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
/**
 * Repository class which handles all the business logic & all the DB Operations.
 */
class NycSchoolsRepositoryImpl @Inject constructor(
    private val nycSchoolApiService: NycSchoolApiService,
    private val nycSchoolDao: NycSchoolDao
) : NycSchoolsRepository {
    override suspend fun getNetworkSchools(forceRefresh: Boolean): NycSchoolData {
        if(!forceRefresh && getAllRecords().isNotEmpty())
            return NycSchoolData.ListOfSchools(getAllRecords())

        val response = getResult {
            nycSchoolApiService.getListOfSchools()
        }
        if (response.status == Status.SUCCESS) {
            response.data?.let {
                insertHighSchoolRecordsInDB(it)
                updateSatScores()
                return NycSchoolData.ListOfSchools(getAllRecords())
            }
        }
        return NycSchoolData.Failure("")
    }

    override suspend fun insertHighSchoolRecordsInDB(listOFRecord: List<NycSchool>) {
        clearAllTheRecords()
        listOFRecord.forEach {
            nycSchoolDao.insertSchoolInDb(it)
        }
    }

    override suspend fun getAllRecords(): List<NycSchool> {
        return nycSchoolDao.getSchoolsList()
    }

    override suspend fun updateSatScores() {

        val response = getResult {
            nycSchoolApiService.getListOfSatScores()
        }
        if (response.status == Status.SUCCESS) {
            response.data?.let {
                updateNycSchoolRecords(it)
            }
        }
    }

    override suspend fun updateNycSchoolRecords(listOfRecords: List<NycSchool>?) {
        listOfRecords?.forEach { nycSchool ->
            nycSchool.apply {
                nycSchoolDao.updateSatScoresInDb(
                    dbn,
                    satTestTakers,
                    satCriticalReadingAvgScore,
                    satMathAvgTakers,
                    satWritingAvgScore
                )
            }
        }
    }

    override suspend fun getNycSchoolRecord(id: String): NycSchool {
        return nycSchoolDao.getRecordFromId(id)
    }

    override suspend fun clearAllTheRecords() {
        nycSchoolDao.dropAllTheRecords()
    }

    private suspend fun <T> getResult(call: suspend () -> Response<T>): ResponseStatus<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResponseStatus.success(body)
            }
            return ResponseStatus.error(HttpException(response))
        } catch (exception: Exception) {
            return ResponseStatus.error(exception)
        }
    }

}