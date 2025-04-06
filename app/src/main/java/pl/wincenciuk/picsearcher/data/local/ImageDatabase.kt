package pl.wincenciuk.picsearcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.wincenciuk.picsearcher.data.model.Hit

@Database(entities = [Hit::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}