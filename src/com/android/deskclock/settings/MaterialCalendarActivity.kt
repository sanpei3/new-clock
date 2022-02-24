package com.best.deskclock.settings

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnDayClickListener

import com.best.deskclock.data.Holidays
import com.best.deskclock.R

import java.util.*

class MaterialCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_holiday)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        val context: Context = getApplicationContext()
        val titleResourceId = intent.getIntExtra(EXTRA_TITLE, 0)
        setTitle(context.getString(titleResourceId))

        val min = Calendar.getInstance()
        min.add(Calendar.DAY_OF_MONTH, -1);
        calendarView.setMinimumDate(min)

        var holidays = Holidays()
//        calendarView.setSelectedDates(holidays.getHolidays())
        calendarView.setHighlightedDays(holidays.getHolidays())

        calendarView.setOnDayClickListener(OnDayClickListener { eventDay ->
             //currently, do nothing(only show holidays)
            //val nowCalendar = eventDay.calendar

            Toast.makeText(
                applicationContext,
                "Even you select the holidays, it is not added to the holidays list(not yet implemented)."
            ,Toast.LENGTH_LONG
            ).show()
            //Toast.makeText(
            //    applicationContext,
            //    nowCalendar.get(Calendar.YEAR).toString() + "-" +
            //            (nowCalendar.get(Calendar.MONTH) + 1).toString() + "-" +
            //            nowCalendar.get(Calendar.DATE).toString(), Toast.LENGTH_SHORT
            //).show()


        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /** Key to an extra that defines resource id to the title of this activity.  */
        private const val EXTRA_TITLE = "extra_title"
        /**
         * @return an intent that launches the ringtone picker to edit the ringtone of all timers
         */
        @JvmStatic
        @Keep
        fun createMaterialCalenderPickerIntent(context: Context): Intent {
            return Intent(context, MaterialCalendarActivity::class.java)
                .putExtra(EXTRA_TITLE, R.string.open_holiday_settings)
        }
    }
}