/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.best.deskclock.data

import android.content.Context
import android.content.SharedPreferences
import com.best.deskclock.AsyncHandler
import com.best.deskclock.alarms.AlarmStateManager
import java.util.*

class Holidays {


    companion object {
        private var mContext: Context? = null
        private var mPrefs: SharedPreferences? = null
        private val mHolidays = arrayListOf<String>()
        private val default_holidays = arrayOf(
            "2022/03/21",  // Vernal Equinox Day
            "2022/04/29",  // Showa Day
            "2022/05/02",  // Day off for Labor holiday
            "2022/05/03",  // Constitution Memorial Day
            "2022/05/04",  // Greenery day
            "2022/05/05",  // Children's day
            "2022/07/18",  // Marine day
        )

        /** Key to an extra that defines resource id to the title of this activity.  */
        private const val KEY_HOLIDAYS = "holidays"

        fun getHolidays() :MutableList<Calendar> {
            val calendars: MutableList<Calendar> = ArrayList()
            for (holiday in mHolidays) {
                var h = holiday.split("/")
                var year = h[0].toInt()
                var month = h[1].toInt() - 1
                var dayofMonth = h[2].toInt()
                val calendar = Calendar.getInstance()
                calendar[year, month] = dayofMonth
                calendars.add(calendar)
            }
            return calendars
        }

        fun isHoliday(time: Calendar): Boolean {
            for (holiday in mHolidays) {
                var h = holiday.split("/")
                var month = h[1]
                if (time[Calendar.YEAR].equals(h[0].toInt()) &&
                    time[Calendar.MONTH].equals(h[1].toInt() - 1) &&
                    time[Calendar.DAY_OF_MONTH].equals(h[2].toInt())) {
                    return true
                }
            }
            return false
        }
    }
    fun init(context: Context, prefs: SharedPreferences) {
        if (mContext != context) {
            mContext = context
            mPrefs = prefs
            var prefsHolidays = prefs.getString(KEY_HOLIDAYS, null)
            if (prefsHolidays == null) {
                // 空っぽならば、新規につくる
                mHolidays.clear()
                for (holiday in default_holidays) {
                    mHolidays.add(holiday)
                }
                // prefsにmHolidays を変換して書き込む
                writeHolidaysToPrefs()
            } else {
                mHolidays.clear()
                for (holiday in prefsHolidays.split(",")) {
                    if (holiday != "") {
                        mHolidays.add(holiday)
                    }
                }
            }
        }
    }

    fun getHolidaysByStrings() :String {
        var strHolidays = ""
        for (holiday in mHolidays) {
            if (strHolidays != "") {
                strHolidays = strHolidays + ","
            }
            strHolidays = strHolidays + holiday
        }
        return strHolidays
    }

    // XX writeHolidaysTo
    private fun writeHolidaysToPrefs () {
        var strHolidays = ""
        for (holiday in mHolidays) {
            strHolidays = strHolidays + holiday + ","
        }
        val editor = mPrefs!!.edit()
        //editor.putString(KEY_HOLIDAYS, holidays.getHolidaysString)
        editor.putString(KEY_HOLIDAYS, strHolidays)
        //editor.commit();
        editor.apply()
    }

    // XX
    fun setHolidays (holidays: List<Calendar>) {
        // XXholidaysをmHolidaysに設定する
        if (holidays == null) {
            return
        } else {
            mHolidays.clear()
            for (holiday in holidays) {
                var str = holiday[Calendar.YEAR].toString() + "/" +
                    (holiday[Calendar.MONTH] + 1).toString() + "/" +
                    holiday[Calendar.DAY_OF_MONTH].toString()
                mHolidays.add(str)
            }
            writeHolidaysToPrefs()
            AsyncHandler.post {
                try {
                    // Update all the alarm instances on time change event
                    mContext?.let { AlarmStateManager.fixAlarmInstances(it) }
                } finally {

                }
            }
        }
    }


}
