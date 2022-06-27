package com.aarontunney.catfacts

// Simple data class, with property names matching the names found in the JSON file
data class Fact(val id: String,
                val user: String,
                val text: String,
                val source: String,
                val updatedAt: String,
                val type: String,
                val createdAt: String,
                val deleted: Boolean,
                val used: Boolean) {
    data class Status(val verified: Boolean, val sentCount: Int) {}
}