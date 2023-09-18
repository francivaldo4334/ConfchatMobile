package br.com.confchat.mobile.di

import br.com.confchat.mobile.domain.AuthDomainRepository
import br.com.confchat.mobile.domain.IAuthDomainRepository
import br.com.confchat.mobile.domain.IUserDomainRepository
import br.com.confchat.mobile.domain.UserDomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StanceRepository {
    @Binds
    @Singleton
    abstract fun bindAuthDomainRepository(it: AuthDomainRepository): IAuthDomainRepository
    @Binds
    @Singleton
    abstract fun bindUserDomainRepository(it: UserDomainRepository) : IUserDomainRepository
}