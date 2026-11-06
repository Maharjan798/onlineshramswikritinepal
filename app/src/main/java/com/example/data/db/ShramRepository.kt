package com.example.data.db

import kotlinx.coroutines.flow.Flow

class ShramRepository(private val dao: ShramDao) {

    val savedEstimates: Flow<List<SavedEstimateEntity>> = dao.getAllSavedEstimates()
    val checklistStates: Flow<List<ChecklistStateEntity>> = dao.getAllChecklistStates()

    suspend fun saveEstimate(estimate: SavedEstimateEntity): Long {
        return dao.insertEstimate(estimate)
    }

    suspend fun deleteEstimate(id: Long) {
        dao.deleteEstimateById(id)
    }

    suspend fun updateChecklistState(documentId: String, isCompleted: Boolean, note: String = "") {
        dao.insertOrUpdateChecklistState(
            ChecklistStateEntity(documentId = documentId, isCompleted = isCompleted, userNote = note)
        )
    }

    suspend fun resetChecklist() {
        dao.clearChecklistStates()
    }
}
