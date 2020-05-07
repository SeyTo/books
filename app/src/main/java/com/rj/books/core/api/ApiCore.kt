package com.rj.books.core.api

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rj.books.core.api.interceptor.GenericRequestInterceptor
import com.rj.books.utils.RxUtils
import com.rj.books.utils.gson
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private object ApiCall {

    private var httpClient: OkHttpClient? = null
    private var core: Retrofit? = null
    private var request: GenericDefinition? = null

    fun httpClient(context: Context): OkHttpClient {
        if (httpClient == null)
            httpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(GenericRequestInterceptor())
//                .addInterceptor(ReAuthInterceptor(context))
                .build()
        return httpClient!!
    }

    fun core(context: Context): Retrofit {
        val baseUrl = ""
        if (core == null)
            core = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient(context))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return core!!
    }

    fun request(context: Context): GenericDefinition {
        if (request == null)
            request = core(context).create(GenericDefinition::class.java)
        return request!!
    }
}

interface GenericDefinition {
    @POST
    fun post(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body request: JsonObject
    ): Observable<JsonObject>

    @GET
    fun get(
        @Url url: String,
        @QueryMap queryMap: Map<String, String>,
        @HeaderMap headers: Map<String, String>
    ): Observable<JsonObject>

    @Multipart
    @POST
    fun multipart(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @PartMap body: Map<String, RequestBody>,
        @Part files: List<MultipartBody.Part>
    ): Observable<JsonObject>

}

/**
 * Request an http object from server.
 */
fun request(context: Context, urlName: String, req: JsonObject = JsonObject()): Observable<JsonObject> {
    val meta = getRequestMeta(urlName) ?: throw Error("No url name with $urlName found")

    // work with headers
    val headers = HashMap<String, String>()
    applyHeaders(context, meta, headers)

    return when (meta.method) {
        Methods.DELETE -> {
            TODO("Apply delete method")
        }
        Methods.GET -> {
            ApiCall
                .request(context)
                .get(meta.url!!, applyQueryParams(meta, req), headers)
                .compose(RxUtils.defaultTransformers())
        }
        Methods.POST -> {
            applyExtraBodies(meta, req)
            ApiCall
                .request(context)
                .post(meta.url!!, headers, req)
                .compose(RxUtils.defaultTransformers())
        }
        Methods.PUT -> {
            TODO("Apply Put Method")
        }
    }
}

/**
 * Create headers map again by reapplying missing parameters.
 * @param context
 */
@SuppressLint("DefaultLocale")
private fun applyHeaders(context: Context, requestMeta: RequestMeta, headers: HashMap<String, String>) {
    // only apply if authHeaders is not null
    requestMeta.authHeaders?.apply {
        // check if auth is missing
        val auth = this["Authorization"]
        if (auth != null && auth.isNotEmpty() && (auth.trim().toLowerCase() == "bearer")) { // basically a useless authorization
            TODO("Handle auth is empty")
        }
        headers.putAll(requestMeta.authHeaders)
    }
}

private fun applyExtraBodies(meta: RequestMeta, req: JsonObject) {
    meta.extraBodies?.forEach { entry ->
        req.add(entry.key, entry.value)
    }
}

/**
 * Generate query params from given request body.
 */
private fun applyQueryParams(meta: RequestMeta, req: JsonObject): Map<String, String> {
    val headers: HashMap<String, String> = HashMap()
    meta.queries?.forEach {
        // check if contained in req, if yes then copy to headers
        if (!req.get(it.key).isJsonNull)
            headers += Pair(it.key, req.get(it.key).asString)
    }
    return headers
}

private fun getRequestMeta(urlName: String): RequestMeta {
    return UrlContracts.forName(urlName)
}

/**
 * @param url
 * @param queries
 * @param authHeaders
 * @param method [ 'POST', 'GET', 'DELETE', 'PUT' ]
 */
data class RequestMeta(
    val url: String,
    val queries: Map<String, String>? = null,
    val authHeaders: Map<String, String>? = null,
    val method: Methods = Methods.POST,
    val extraBodies: Map<String, JsonElement>? = null
)

enum class Methods {
    GET, POST, DELETE, PUT
}

