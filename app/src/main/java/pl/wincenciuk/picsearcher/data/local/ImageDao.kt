package pl.wincenciuk.picsearcher.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.wincenciuk.picsearcher.data.model.Hit

@Dao
interface ImageDao {
    @Query("SELECT * FROM hit")
    suspend fun getAllImages(): List<Hit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(image: List<Hit>)
}