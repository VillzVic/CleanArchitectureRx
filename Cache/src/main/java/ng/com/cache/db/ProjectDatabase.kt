package ng.com.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ng.com.cache.dao.CachedProjectsDao
import ng.com.cache.dao.ConfigDao
import ng.com.cache.model.CachedProject
import ng.com.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedProject::class,  Config::class), version = 1)
abstract class ProjectDatabase @Inject constructor() :RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao
    abstract fun configDao(): ConfigDao

    companion object {
        private var INSTANCE : ProjectDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ProjectDatabase{
            if(INSTANCE == null){
                synchronized(lock){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProjectDatabase::class.java, "projects.db").build()
                    }
                    return INSTANCE as ProjectDatabase
                }
            }
            return INSTANCE as ProjectDatabase
        }
    }
}