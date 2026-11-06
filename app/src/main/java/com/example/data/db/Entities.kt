package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_estimates")
data class SavedEstimateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val countryId: String,
    val countryNameEn: String,
    val countryNameNe: String,
    val flagEmoji: String,
    val age: Int,
    val contractYears: Int,
    val welfareFundNpr: Double,
    val insurancePremiumNpr: Double,
    val ssfContributionNpr: Double,
    val gatewayFeeNpr: Double,
    val totalNpr: Double,
    val currencyCode: String,
    val totalLocalCurrency: Double
)

@Entity(tableName = "checklist_states")
data class ChecklistStateEntity(
    @PrimaryKey val documentId: String,
    val isCompleted: Boolean,
    val userNote: String = ""
)
