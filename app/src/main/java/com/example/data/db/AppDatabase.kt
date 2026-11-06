package com.example.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface ShramDao {
    @Query("SELECT * FROM saved_estimates ORDER BY timestamp DESC")
    fun getAllSavedEstimates(): Flow<List<SavedEstimateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimate(estimate: SavedEstimateEntity): Long

    @Query("DELETE FROM saved_estimates WHERE id = :id")
    suspend fun deleteEstimateById(id: Long)

    @Query("DELETE FROM saved_estimates")
    suspend fun clearEstimates()

    @Query("SELECT * FROM checklist_states")
    fun getAllChecklistStates(): Flow<List<ChecklistStateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateChecklistState(state: ChecklistStateEntity)

    @Query("DELETE FROM checklist_states")
    suspend fun clearChecklistStates()
}

@Database(entities = [SavedEstimateEntity::class, ChecklistStateEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shramDao(): ShramDao
}
