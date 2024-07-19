package id.eve.mygithubapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import id.eve.core.data.local.room.UserDao
import org.koin.android.ext.android.inject

class FavoriteProvider : ContentProvider() {

    companion object {
        private const val AUTH = "com.xridwan.mygithub"
        private const val TABLE_NAME = "favorite_tb"
        private const val ID_FAVORITE = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTH, TABLE_NAME, ID_FAVORITE)
        }
    }

    private val userDao get() = inject<UserDao>()

    override fun onCreate(): Boolean {
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?,
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_FAVORITE -> {
                cursor = userDao.value.getData()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }

            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?,
    ): Int {
        return 0
    }
}