package com.rj.books.model.login.data

import android.content.Context
import com.rj.books.model.Result
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val remoteDataSource: LoginRemoteDataSource) {

    // in-memory cache of the loggedInUser object
    companion object {
        var user: LoggedInUser? = null
            private set

        val isLoggedIn: Boolean
            get() = user != null
    }


    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        remoteDataSource.logout()
    }

    fun login(context: Context, username: String, password: String): Single<Result<LoggedInUser>> {
        return remoteDataSource.login(context, username, password)
            .map { result ->
                if (result is Result.Success) {
                    setLoggedInUser(result.data)
                }
                return@map result
            }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    fun register(
        context: Context,
        fullName: String,
        userName: String,
        password: String
    ): Single<Result<RegisteredUser>> {
        return remoteDataSource.register(context, fullName, userName, password)
    }
}
