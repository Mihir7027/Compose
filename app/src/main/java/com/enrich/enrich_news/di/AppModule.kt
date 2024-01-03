package com.enrich.enrich_news.di

import android.app.Application
import androidx.room.Room
import com.enrich.enrich_news.data.local.NewsDao
import com.enrich.enrich_news.data.local.NewsDataBase
import com.enrich.enrich_news.data.local.NewsTypeConverter
import com.enrich.enrich_news.data.manager.LocalUserManagerImpl
import com.enrich.enrich_news.data.remote.ApiInterface
import com.enrich.enrich_news.data.repository.RemoteRepositoryImpl
import com.enrich.enrich_news.domain.manager.LocalUserManager
import com.enrich.enrich_news.domain.repository.RemoteRepository
import com.enrich.enrich_news.domain.usecases.app_entry.AppEntryUseCases
import com.enrich.enrich_news.domain.usecases.app_entry.ReadAppEntry
import com.enrich.enrich_news.domain.usecases.app_entry.SaveAppEntry
import com.enrich.enrich_news.domain.usecases.news.DeleteArticle
import com.enrich.enrich_news.domain.usecases.news.GetNews
import com.enrich.enrich_news.domain.usecases.news.NewsUseCases
import com.enrich.enrich_news.domain.usecases.news.SelectArticle
import com.enrich.enrich_news.domain.usecases.news.SelectArticleByUrl
import com.enrich.enrich_news.domain.usecases.news.UpsertArticle
import com.enrich.enrich_news.util.Constants
import com.enrich.enrich_news.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides a LocalUserManager instance for managing local user data.
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    // Provides AppEntryUseCases for handling app entry use cases.
    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases {
        return AppEntryUseCases(ReadAppEntry(localUserManager), SaveAppEntry(localUserManager))
    }

    // Provides an instance of ApiInterface for making API requests.
    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(ApiInterface::class.java)
    }

    // Provides a NewsDataBase instance using Room for local data storage.
    @Provides
    @Singleton
    fun provideDatabase(application: Application): NewsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = Constants.DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }


    // Provides a NewsDao instance for accessing local database operations.
    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: NewsDataBase): NewsDao {
        return newsDataBase.newsDao
    }

    // Provides a RemoteRepository instance for handling remote data operations.
    @Provides
    @Singleton
    fun provideRemoteRepository(apiInterface: ApiInterface): RemoteRepository {
        return RemoteRepositoryImpl(apiInterface = apiInterface)
    }


    // Provides NewsUseCases for handling news-related use cases.
    @Provides
    @Singleton
    fun provideNewsUseCases(remoteRepository: RemoteRepository, newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(remoteRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticle = SelectArticle(newsDao),
            selectArticleByUrl = SelectArticleByUrl(newsDao),
        )
    }


}