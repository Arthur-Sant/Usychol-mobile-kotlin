package com.project.usychol.shared

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.temporal.ChronoUnit.YEARS
import kotlin.contracts.Returns

class DataFormat {
    private var arrayMonths = mapOf<String, Int>(
        "january" to 1,
        "february" to 2,
        "march" to 3,
        "april" to 4,
        "may" to 5,
        "june" to 6,
        "july" to 7,
        "august" to 8,
        "september" to 9,
        "october" to 10,
        "november" to 11,
        "december" to 12
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDiffYear(monthString: String, day: String, year: String ): String {
        return try {
            val month = arrayMonths.get(monthString.lowercase())
            val date = LocalDate.of(year.toInt(), month!!, day.toInt())
            val dateNow = LocalDate.now()
            YEARS.between(date, dateNow).toString()
        }catch (exception: Exception){
            ""
        }
    }

    fun getMonth(month: String): String{
        val dayInt = arrayMonths.get(month.lowercase())
        return if(dayInt != null){
            if(dayInt < 10){
                "0$dayInt"
            }else{
                "$dayInt"
            }
        }else{
            ""
        }
    }
}