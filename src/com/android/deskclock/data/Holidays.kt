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

import java.util.*
import java.util.Calendar

public class Holidays {
    companion object {
        private val holidays = arrayOf(
            "2021/12/31",  // Test day
            "2022/01/01",  // Western New Year's Day
            "2022/01/02",
            "2022/01/03",
            "2022/01/04",  // Company holiday
            "2022/01/10",  // Coming of age day
            "2022/01/24",  // Personal Holiday
            "2022/02/11",  // National Foundation Day(Japan)
            "2022/02/23",  // The emperor's birthday(Japan)
        )
    }

    fun isHoliday(time: Calendar): Boolean {
        for (holiday in holidays) {
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
