package com.example.testnotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.testnotifications.RemindersManager.REMINDER_NOTIFICATION_REQUEST_CODE
import java.util.Calendar
import java.util.Locale

fun startReminder(
    context: Context,
    reminderTime: String = "08:00",
    reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val (hours, min) = reminderTime.split(":").map { it.toInt() }
    val intent =
        Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context.applicationContext,
                reminderId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

    val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, min)
    }

    // If the trigger time you specify is in the past, the alarm triggers immediately.
    // if soo just add one day to required calendar
    // Note: i'm also adding one min cuz if the user clicked on the notification as soon as
    // he receive it it will reschedule the alarm to fire another notification immediately
    if (Calendar.getInstance(Locale.ENGLISH)
            .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0
    ) {
        calendar.add(Calendar.DATE, 1)
    }

    alarmManager.setAlarmClock(
        AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
        intent
    )
}