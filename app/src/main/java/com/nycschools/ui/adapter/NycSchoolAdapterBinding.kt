package com.nycschools.ui.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nycschools.R
import com.nycschools.room.NycSchool


/**
 * Binding adapter is responsible to set the
 * data which is retrieved from the School object.
 */

@BindingAdapter(value = ["nycSchoolObject"])
fun TextView.setScores(nycSchoolObject: NycSchool?) {
    text = getAverageMathScore(context, nycSchoolObject)
}

private fun getAverageMathScore(context: Context, nycSchool: NycSchool?): Spanned {
    StringBuilder().apply {
        append(context.getString(R.string.sat_average))
        val bulletOneStartIndex = length
        append(
            context.getString(R.string.sat_avg_math_score, nycSchool?.satMathAvgTakers)
        )
        val bulletOneEndIndex = length
        append(
            context.getString(
                R.string.sat_critical_reading_avg_score,
                nycSchool?.satCriticalReadingAvgScore
            )
        )
        val bulletTwoEndIndex = length
        append(
            context
                .getString(R.string.sat_writing_avg_score, nycSchool?.satWritingAvgScore)
        )
        val bulletThreeEndIndex = length
        also {
            SpannableStringBuilder(this).apply {
                setSpan(
                    BulletSpan(30),
                    bulletOneStartIndex,
                    bulletOneEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    BulletSpan(30),
                    bulletOneEndIndex,
                    bulletTwoEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    BulletSpan(30),
                    bulletTwoEndIndex,
                    bulletThreeEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                return this
            }
        }
    }
}