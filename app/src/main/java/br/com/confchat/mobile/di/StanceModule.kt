package br.com.confchat.mobile.di

import br.com.confchat.mobile.data.network.repository.AuthApiRepository
import br.com.confchat.mobile.data.network.repository.IAuthApiRepository
import br.com.confchat.mobile.data.network.service.ApiConfchatService
import br.com.confchat.mobile.domain.AuthDomainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StanceModule {
    private val BASE_URL="http://ec2-52-67-244-32.sa-east-1.compute.amazonaws.com/"
    private val authInterceptor = Interceptor{chain->
        val request = chain.request();
        val newRequest =
            if(request.url.pathSegments.contains("auth")) {
                request
            }
            else{
                val token = ""
                val newHeaders = request.headers.newBuilder()
                    .add("Authorization","Bearer $token")
                    .build()
                val newRequestBuilder = request.newBuilder().headers(newHeaders)
                newRequestBuilder.build()
            }
        chain.proceed(newRequest)
    }
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Nível de logging: BASIC, HEADERS, BODY
    }
    @Provides
    @Singleton
    fun getRetrofit(): ApiConfchatService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiConfchatService::class.java)
    @Provides
    @Singleton
    fun getClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
//        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
    @Provides
    @Singleton
    fun providerAuthApiRepository(it:ApiConfchatService): IAuthApiRepository {
        return AuthApiRepository(it)
    }
    @Provides
    @Singleton
    fun providerAuthDomainRepository(it: IAuthApiRepository): AuthDomainRepository{
        return AuthDomainRepository(it)
    }
}