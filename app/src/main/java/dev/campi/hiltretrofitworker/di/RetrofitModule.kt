package dev.campi.hiltretrofitworker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.campi.hiltretrofitworker.data.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    private const val ENDPOINT = "https://jsonplaceholder.typicode.com/"
//    private const val TIMEOUT = 600L
//    @Singleton
//    @Provides
//    fun provideInterceptor(): HttpLoggingInterceptor =
//        HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    @Singleton
//    @Provides
//    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
//        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
//            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .build()

    @Singleton
    @Provides
    fun provideGson() : Gson =
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
//    fun provideRetrofit(client: OkHttpClient, gson: Gson) : Retrofit.Builder =
    fun provideRetrofit(gson: Gson) : Retrofit.Builder =
        Retrofit.Builder().baseUrl(ENDPOINT)
//            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)) // gson
            .addCallAdapterFactory(CoroutineCallAdapterFactory()) // coroutine

    @Singleton
    @Provides
    fun provideAccionesRetrofit(retrofit: Retrofit.Builder) : ApiService =
        retrofit.build().create(ApiService::class.java)
}

interface  ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}

