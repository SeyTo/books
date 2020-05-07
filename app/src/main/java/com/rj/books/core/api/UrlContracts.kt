package com.rj.books.core.api

import com.google.gson.JsonElement
import com.rj.books.BuildConfig
import com.rj.books.core.SessionModel

object UrlContracts {

    object Name {
        val BASE: String = BuildConfig.SERVERS[BuildConfig.SERVER_INDEX]
        object BOOK {
            val BASE = "${Name.BASE}/books"
            val GET_BOOK = BASE
        }

        object PROFILE {
            val BASE = "${Name.BASE}/user"
            val GET_PROFILE = BASE
            val LOGIN = "$BASE/login"
            val REGISTER = "$BASE/register"
        }
    }

    @Synchronized
    fun forName(urlName: String = ""): RequestMeta {
        if (urlName.isEmpty()) throw Error("Url name cannot be null or empty")

        // constant components
        val authHeader = HashMap<String, String>()
        authHeader["Authorization"] = "Bearer " + SessionModel.loggedInUser?.accessToken

        val extraBody = HashMap<String, JsonElement>()

        return when {
            urlName.contains(Name.BOOK.BASE) -> {
                books(urlName, authHeader, extraBody)
            }
            urlName.contains(Name.PROFILE.BASE) -> {
                profile(urlName, authHeader, extraBody)
            }
            else -> {
                throw RuntimeException("Unknown base of given url $urlName")
            }
        }
    }

    // === sub processes === //

    private fun books(urlName: String, _authHeaders: HashMap<String, String>, _extraBodies: HashMap<String, JsonElement>): RequestMeta {
        var method: Methods = Methods.GET
        var queries: Map<String, String>? = null
        var authHeaders: HashMap<String, String>? = null
        var extraBodies: HashMap<String, JsonElement>? = null
        when (urlName) {
            Name.BOOK.GET_BOOK -> {
                method = Methods.GET
            }
            else -> {
                TODO("$urlName has not been added yet")
            }

        }
        return RequestMeta(
            url = urlName,
            method = method,
            queries = queries,
            authHeaders = authHeaders,
            extraBodies = extraBodies
        )
    }

    private fun profile(urlName: String, _authHeaders: HashMap<String, String>, _extraBodies: HashMap<String, JsonElement>): RequestMeta {
        var method: Methods = Methods.GET
        var queries: Map<String, String>? = null
        var authHeaders: HashMap<String, String>? = null
        var extraBodies: HashMap<String, JsonElement>? = null
        when (urlName) {
            Name.PROFILE.GET_PROFILE -> {
                method = Methods.GET
            }
            Name.PROFILE.LOGIN -> {
                method = Methods.POST
            }
            Name.PROFILE.REGISTER -> {
                method = Methods.POST
            }
            else -> {
                TODO("$urlName has not been added yet")
            }
        }
        return RequestMeta(
            url = urlName,
            method = method,
            queries = queries,
            authHeaders = authHeaders,
            extraBodies = extraBodies
        )
    }
}
