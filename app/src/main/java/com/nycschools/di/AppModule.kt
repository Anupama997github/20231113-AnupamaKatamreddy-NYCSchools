package com.nycschools.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nycschools.mvvm.repository.NycSchoolsRepository
import com.nycschools.mvvm.repository.NycSchoolsRepositoryImpl
import com.nycschools.mvvm.usecase.ListSchoolsUsecase
import com.nycschools.network.NycSchoolApiService
import com.nycschools.room.NycSchoolDao
import com.nycschools.room.NycSchoolDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClinent() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideListOfSchools(nycSchoolsRepository: NycSchoolsRepository) =
        ListSchoolsUsecase(nycSchoolsRepository)

    @Singleton
    @Provides
    fun provideListOfSchoolsRepository(
        nycSchoolApiService: NycSchoolApiService,
        nycSchoolDao: NycSchoolDao
    ): NycSchoolsRepository =
        NycSchoolsRepositoryImpl(nycSchoolApiService, nycSchoolDao)

    @Provides
    fun provideNycSchoolApiService(retrofit: Retrofit) =
        retrofit.create(NycSchoolApiService::class.java)

    @Provides
    fun provideSchoolDao(nycSchoolDatabase: NycSchoolDatabase): NycSchoolDao =
        nycSchoolDatabase.getSchoolDao()

    @Provides
    fun provideNycSchoolDatabase(@ApplicationContext context: Context): NycSchoolDatabase =
        Room.databaseBuilder(context, NycSchoolDatabase::class.java, "nyc-school-db")
            .allowMainThreadQueries().build()


    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/"
        const val TIMEOUT = 30L // In Secs
    }
}