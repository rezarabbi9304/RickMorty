package com.dentonstudio.rickandmorty.di

import com.dentonstudio.rickandmorty.data.remote.CharacterApi
import com.dentonstudio.rickandmorty.data.remote.repository.repositoryImp
import com.dentonstudio.rickandmorty.domain.repository.CharacterRepository
import com.dentonstudio.rickandmorty.domain.use_case.GetCharacterDetailsCase
import com.dentonstudio.rickandmorty.domain.use_case.getCharacterCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRickMortyApi(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit):CharacterApi{
        return  retrofit.create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterApi ): CharacterRepository {
        return  repositoryImp(api = api)
    }

    @Provides
    @Singleton
    fun provideCharacterCase(repository: CharacterRepository): getCharacterCase {
        return  getCharacterCase(repository)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsCase(repository: CharacterRepository): GetCharacterDetailsCase {
        return  GetCharacterDetailsCase(repository)
    }
}