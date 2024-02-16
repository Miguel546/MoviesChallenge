package com.luismiguel.movieschallenge.data.database

import android.content.Context
import androidx.room.Room
import com.luismiguel.movieschallenge.data.dao.MovieFlowDao
import com.luismiguel.movieschallenge.data.repository.LocalRepositoryImpl
import com.luismiguel.movieschallenge.domain.repository.ILocalRepository
import com.luismiguel.movieschallenge.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MovieFlowDatabase {
        return Room.databaseBuilder(context, MovieFlowDatabase::class.java, Constants.DATABASE_APP).build()
    }

    @Singleton
    @Provides
    fun provideQuoteDao(db: MovieFlowDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(tareaDao: MovieFlowDao): ILocalRepository {
        return LocalRepositoryImpl(tareaDao)
    }
}