package br.com.confchat.mobile.di

import android.content.Context
import br.com.confchat.mobile.R
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.database.AppDatabase
import br.com.confchat.mobile.data.database.repository.implementation.PaymentRepository
import br.com.confchat.mobile.data.database.repository.contract.IPaymentRepository
import br.com.confchat.mobile.data.network.repository.confchat.AuthApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.ChatApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.IAuthApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.IChatApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.IUserApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.UserApiRepository
import br.com.confchat.mobile.data.network.repository.pagbank.ApiPagBankRepository
import br.com.confchat.mobile.data.network.repository.pagbank.IApiPagBankRepository
import br.com.confchat.mobile.data.network.service.ApiConfchatService
import br.com.confchat.mobile.data.network.service.ApiaPagBankService
import br.com.confchat.mobile.domain.repository.implementation.AuthDomainRepository
import br.com.confchat.mobile.domain.repository.implementation.ChatDomainRepository
import br.com.confchat.mobile.domain.repository.implementation.ConfchatDbDomainRepository
import br.com.confchat.mobile.domain.repository.contract.IChatDomainRepository
import br.com.confchat.mobile.domain.repository.contract.IConfchatDbDomainRepository
import br.com.confchat.mobile.domain.repository.contract.IPagBankDomainRepository
import br.com.confchat.mobile.domain.repository.implementation.PagBankDomainRepository
import br.com.confchat.mobile.domain.repository.implementation.UserDomainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    public var urlTest = ""
    private val authInterceptor = Interceptor{chain->
        val request = chain.request();
        val newRequest =
            if(request.url.pathSegments.contains("auth")) {
                request
            }
            else{
                val token = MyConstants.TOKEN
                val newHeaders = request.headers.newBuilder()
                    .add("Authorization","Bearer $token")
                        .build()
                val newRequestBuilder = request.newBuilder().headers(newHeaders)
                newRequestBuilder.build()
            }
        chain.proceed(newRequest)
    }
    private val authInterceptorPagBank = Interceptor{chain->
        val request = chain.request();
        val newRequest =
            if(request.url.pathSegments.contains("auth")) {
                request
            }
            else{
                val token = MyConstants.TOKEN
                val newHeaders = request.headers.newBuilder()
                    .add("Authorization","Bearer 277C6113D1544B0A9F6887B1F1CCA136")
                    .build()
                val newRequestBuilder = request.newBuilder().headers(newHeaders)
                newRequestBuilder.build()
            }
        chain.proceed(newRequest)
    }
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun getRetrofit(
        @ApplicationContext context: Context?
    ): ApiConfchatService = Retrofit.Builder()
        .baseUrl(context?.getString(R.string.base_url)?:urlTest)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiConfchatService::class.java)
    @Provides
    @Singleton
    fun getClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
    @Provides
    @Singleton
    fun providerAuthApiRepository(it:ApiConfchatService): IAuthApiRepository {
        return AuthApiRepository(it)
    }
    @Provides
    @Singleton
    fun providerAuthDomainRepository(auth: IAuthApiRepository, user: IUserApiRepository, @ApplicationContext context: Context): AuthDomainRepository {
        return AuthDomainRepository(auth,user,context)
    }

    @Provides
    @Singleton
    fun providerUserApiRepository(it:ApiConfchatService): IUserApiRepository {
        return UserApiRepository(it)
    }
    @Provides
    @Singleton
    fun providerChatApiRepository(it:ApiConfchatService): IChatApiRepository {
        return ChatApiRepository(it)
    }
    @Provides
    @Singleton
    fun providerUserDomainRepository(it: IUserApiRepository): UserDomainRepository {
        return UserDomainRepository(it)
    }
    @Provides
    @Singleton
    fun providerChatDomaiRepository(it: IChatApiRepository): IChatDomainRepository {
        return ChatDomainRepository(it)
    }
    @Provides
    @Singleton
    fun getRetrofitPagBank(
    ): ApiaPagBankService = Retrofit.Builder()
        .baseUrl("https://sandbox.api.pagseguro.com/")
        .client(getClientPagBank())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiaPagBankService::class.java)
    fun getClientPagBank() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(authInterceptorPagBank)
        .addInterceptor(loggingInterceptor)
        .build()
    @Provides
    @Singleton
    fun providerPagbankApiRepository(it:ApiaPagBankService): IApiPagBankRepository {
        return ApiPagBankRepository(it)
    }
    @Provides
    @Singleton
    fun providerPayment(@ApplicationContext context: Context): IPaymentRepository {
        return PaymentRepository(AppDatabase.getInstance(context).paymentDao())
    }
    @Provides
    @Singleton
    fun providerProduct(@ApplicationContext context: Context): IPaymentRepository {
        return PaymentRepository(AppDatabase.getInstance(context).paymentDao())//TODO
    }
    @Provides
    @Singleton
    fun providerAppPayment(db: IPaymentRepository): IConfchatDbDomainRepository {
        return ConfchatDbDomainRepository(db)
    }
    @Provides
    @Singleton
    fun providerPagbankDomaiRepository(it: IApiPagBankRepository,db: IPaymentRepository): IPagBankDomainRepository {
        return PagBankDomainRepository(it,db)
    }
}