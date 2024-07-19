package id.eve.core.data.preference

import id.eve.core.data.local.entity.Reminder

interface ReminderPreference {
    fun setReminder(value: Reminder)
    fun getReminder(): Reminder
}