package id.eve.core.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reminder(
    var isReminded: Boolean = false
) : Parcelable
