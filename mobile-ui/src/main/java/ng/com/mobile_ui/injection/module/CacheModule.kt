package ng.com.mobile_ui.injection.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ng.com.cache.ProjectCacheImpl
import ng.com.cache.db.ProjectDatabase
import ng.com.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectDatabase {
            return ProjectDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectCacheImpl): ProjectsCache
}