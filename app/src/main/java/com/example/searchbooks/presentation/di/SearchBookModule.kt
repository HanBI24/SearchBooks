package com.example.searchbooks.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.searchbooks.common.Constants.BASE_URL
import com.example.searchbooks.data.local.SearchBookDao
import com.example.searchbooks.data.local.SearchBookDatabase
import com.example.searchbooks.data.remote.api.SearchBookApi
import com.example.searchbooks.domain.model.local.SearchBookLocalRepositoryImpl
import com.example.searchbooks.domain.model.local.repository.SearchBookLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchBookModule {

    @Provides
    @Singleton
    fun provideSearchBookRepository(
        searchBookDao: SearchBookDao
    ): SearchBookLocalRepository {
        return SearchBookLocalRepositoryImpl(searchBookDao)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchBookApi(
        retrofit: Retrofit
    ): SearchBookApi{
        return retrofit.create(SearchBookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchWordDao(
        searchBookDatabase: SearchBookDatabase
    ): SearchBookDao {
        return searchBookDatabase.searchBookDao
    }

    @Provides
    @Singleton
    fun provideSearchBookDatabase(
        @ApplicationContext context: Context
    ): SearchBookDatabase {
        return Room.databaseBuilder(
            context,
            SearchBookDatabase::class.java,
            "searchbookentity"
        ).fallbackToDestructiveMigration()
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideSearchBookPager(
//        searchBookApi: SearchBookApi,
//        @Named("injectSearchWord")
//        searchWord: String
//    ): Pager<Int, Item> {
//        return Pager(PagingConfig(pageSize = Constants.ITEM_PER_PAGE)) {
//            SearchBookPagingSource(searchBookApi, searchWord)
//        }
//    }

//    @Provides
//    @Singleton
//    @Named("injectSearchWord")
//    fun provideSearchWordString(searchWord: String): String {
//        return searchWord
//    }
}