package com.nycschools.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NycSchoolDao {

    @Query("Select * from nyc_school")
    suspend fun getSchoolsList(): List<NycSchool>

    @Insert
    suspend fun insertSchoolInDb(nycSchool: NycSchool)

    @Update
    suspend fun updateSchoolInDb(nycSchool: NycSchool)

    @Query("Update nyc_school SET satTestTakers=:satTestTakers, satCriticalReadingAvgScore=:satCriticalReadingAvgScore, satMathAvgTakers=:satMathAvgTakers, satWritingAvgScore=:satWritingAvgScore WHERE dbn=:schoolId")
    suspend fun updateSatScoresInDb(
        schoolId: String,
        satTestTakers: String,
        satCriticalReadingAvgScore: String,
        satMathAvgTakers: String,
        satWritingAvgScore: String
    )

    @Delete
    suspend fun deleteSchool(nycSchool: NycSchool)

    @Query("DELETE FROM nyc_school")
    suspend fun dropAllTheRecords()

    @Query("SELECT * from nyc_school WHERE dbn=:schoolId")
    suspend fun getRecordFromId(schoolId: String): NycSchool
}