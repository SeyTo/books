package com.rj.books.model.login.data

import android.accounts.AuthenticatorException
import android.content.Context
import com.google.gson.JsonObject
import com.rj.books.core.api.UrlContracts
import com.rj.books.core.api.request
import com.rj.books.model.Result
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginRemoteDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(
                UUID.randomUUID().toString(),
                "Jane Doe",
                true,
                "",
                "",
                ""
            )
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun login(context: Context, username: String, password: String): Single<Result<LoggedInUser>> {
        return request(
            context,
            UrlContracts.Name.PROFILE.LOGIN,
            JsonObject().apply {
                addProperty("username", username)
                addProperty("password", password)
            }
        ).map {
            return@map if (it["success"].asBoolean) {
                Result.Success(LoggedInUser(
                    it["userId"].asString,
                    it["displayName"].asString,
                    true,
                    it["accessToken"].asString,
                    it["refreshToken"].asString,
                    it["timeout"].asString
                ))
            } else {
                Result.Error(AuthenticatorException(it["message"].asString))
            }
        }.firstOrError()
    }

    fun register(context: Context, fullName: String, username: String, password: String): Single<Result<RegisteredUser>> {
        return request(
            context,
            UrlContracts.Name.PROFILE.REGISTER,
            JsonObject().apply {
                addProperty("fullName", fullName)
                addProperty("userName", username)
                addProperty("password", password)
            }
        ).map {
            return@map if (it["success"].asBoolean) {
                Result.Success(RegisteredUser(
                    fullName = fullName,
                    userName = username
                ))
            } else {
                Result.Error(Exception(it["message"].asString))
            }
        }.firstOrError()
    }

    fun logout() {
        TODO("revoke authentication")
    }
}

