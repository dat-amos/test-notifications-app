package com.example.testnotifications

import android.content.Context

object RemindersManager {
    const val REMINDER_NOTIFICATION_REQUEST_CODE = 123
    fun startReminder(
        context: Context,
        reminderTime: String = "19:30",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {}

    fun stopReminder(
        context: Context,
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {}
}