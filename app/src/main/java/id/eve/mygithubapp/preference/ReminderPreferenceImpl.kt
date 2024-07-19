package id.eve.mygithubapp.preference

import android.app.Application
import android.content.Context
import id.eve.core.data.local.entity.Reminder
import id.eve.core.data.preference.ReminderPreference

class ReminderPreferenceImpl(application: Application) : ReminderPreference {

    private val preference = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun setReminder(value: Reminder) {
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminded)
        editor.apply()
    }

    override fun getReminder(): Reminder {
        val model = Reminder()
        model.isReminded = preference.getBoolean(REMINDER, false)
        return model
    }

    companion object {
        const val PREFS_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }
}