package com.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.nycschools.mvvm.model.NycSchoolData
import com.nycschools.mvvm.viewmodel.NycViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NycViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.schoolData.observe(this, ::performNycSchoolData)
        viewModel.getNycSchools()

    }

    fun performNycSchoolData(nycSchoolData: NycSchoolData) {

        when(nycSchoolData) {
            is NycSchoolData.ListOfSchools -> {
                Log.i("ATG", "listPfSchools ${nycSchoolData.listOfSchools}")
            }

            else -> {

            }
        }
    }
}