package id.eve.mygithubapp.presenter.other

import androidx.lifecycle.ViewModel
import id.eve.core.data.local.entity.Reminder
import id.eve.core.data.preference.ReminderPreference

class ReminderViewModel(
    private val preference: ReminderPreference,
) : ViewModel() {

    fun setReminder(value: Reminder) {
        preference.setReminder(value)
    }

    fun getReminder(): Reminder {
        return preference.getReminder()
    }
}

