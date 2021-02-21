package com.mpg.shaadidemoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mpg.shaadidemoapp.data.entity.UserEntity

/**
 * The Room Database that contains the Device and Employee table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}