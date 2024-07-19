package id.eve.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.eve.core.data.local.entity.DetailEntity
import id.eve.core.data.local.entity.Favorite
import id.eve.core.data.local.entity.FollowerEntity
import id.eve.core.data.local.entity.FollowingEntity
import id.eve.core.data.local.entity.UserEntity
import id.eve.core.data.local.room.UserDao

@Database(
    entities = [
        UserEntity::class,
        DetailEntity::class,
        FollowerEntity::class,
        FollowingEntity::class,
        Favorite::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}