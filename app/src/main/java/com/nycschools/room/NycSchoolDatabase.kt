package com.nycschools.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NycSchool::class],
    version = 1,
    exportSchema = false
)
abstract class NycSchoolDatabase : RoomDatabase() {
    abstract fun getSchoolDao(): NycSchoolDao
}