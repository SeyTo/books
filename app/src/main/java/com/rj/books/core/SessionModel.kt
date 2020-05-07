package com.rj.books.core

import com.rj.books.model.login.data.LoggedInUser

/**
 * Should contain all data relating to current session.
 */
object SessionModel {
    var accessToken: String? = null
    var loggedInUser: LoggedInUser? = null
}
