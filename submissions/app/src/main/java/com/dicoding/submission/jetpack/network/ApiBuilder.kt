package com.dicoding.submission.jetpack.network

import com.dicoding.submission.jetpack.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {

    private var retrofit: Retrofit = buildRetrofit(client)

    private val client: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            var builder: OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor {
                    val url = it.request()
                        .url
                        .newBuilder()
                        .build()

                    val request = it.request().newBuilder().url(url).build()
                    it.proceed(request)
                }
            if (BuildConfig.DEBUG) {
                builder = builder.addInterceptor(loggingInterceptor)
            }
            return builder.build()
        }


    private fun buildRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService() = retrofit.create(ApiService::class.java)


}