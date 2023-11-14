package com.nycschools.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entity object: Stores all the school information in DB
 */
@Entity(tableName = "nyc_school")
data class NycSchool(
    @PrimaryKey
    @SerializedName("dbn") var dbn: String = "null",
    @ColumnInfo(name = "schoolName")
    @SerializedName("school_name") var schoolName: String? = null,
    @ColumnInfo(name = "overviewParagraph")
    @SerializedName("overview_paragraph") var overviewParagraph: String? = null,
    @ColumnInfo(name = "academicOpportunities")
    @SerializedName("academicopportunities1") var academicopportunities1: String? = null,
    @ColumnInfo(name = "location")
    @SerializedName("location") var location: String? = null,
    @ColumnInfo(name = "phoneNumber")
    @SerializedName("phone_number") var phoneNumber: String? = null,
    @SerializedName("fax_number") var faxNumber: String? = null,
    @ColumnInfo(name = "schoolEmail")
    @SerializedName("school_email") var schoolEmail: String? = null,
    @ColumnInfo(name = "website")
    @SerializedName("website") var website: String? = null,
    @ColumnInfo(name = "totalStudents")
    @SerializedName("total_students") var totalStudents: String? = null,
    @SerializedName("directions1") var directions1: String? = null,
    @SerializedName("primary_address_line_1") var primaryAddressLine1: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("zip") var zip: String? = null,
    @SerializedName("state_code") var stateCode: String? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @ColumnInfo(name = "satTestTakers")
    @SerializedName("num_of_sat_test_takers") var satTestTakers: String = "",
    @ColumnInfo(name = "satCriticalReadingAvgScore")
    @SerializedName("sat_critical_reading_avg_score") var satCriticalReadingAvgScore: String = "",
    @ColumnInfo(name = "satMathAvgTakers")
    @SerializedName("sat_math_avg_score") var satMathAvgTakers: String = "",
    @ColumnInfo(name = "satWritingAvgScore")
    @SerializedName("sat_writing_avg_score") var satWritingAvgScore: String = ""
)