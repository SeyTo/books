package com.rj.books.model.login.data

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    var isLoggedIn: Boolean = false
)
