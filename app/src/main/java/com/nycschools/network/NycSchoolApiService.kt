package com.nycschools.network

import com.nycschools.room.NycSchool
import retrofit2.Response
import retrofit2.http.GET

interface NycSchoolApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getListOfSchools() : Response<List<NycSchool>>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getListOfSatScores(): Response<List<NycSchool>>
}