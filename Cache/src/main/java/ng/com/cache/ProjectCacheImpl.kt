package ng.com.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ng.com.cache.db.ProjectDatabase
import ng.com.cache.mapper.CacheMapper
import ng.com.cache.mapper.CacheProjectMapper
import ng.com.cache.model.Config
import ng.com.data.model.ProjectEntity
import ng.com.data.repository.ProjectsCache
import javax.inject.Inject

class ProjectCacheImpl @Inject constructor(
    private val mapper: CacheProjectMapper,
    private val projectDatabase: ProjectDatabase
) : ProjectsCache {

    override fun clearProjects(): Completable {
        return  Completable.defer {
            projectDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return  Completable.defer{
            projectDatabase.cachedProjectsDao().insertProjects(
                projects.map {
                    mapper.mapToCache(it)
                }
            )
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {

        return projectDatabase.cachedProjectsDao().getProjects().toObservable().map {
            it.map {
                mapper.mapFromCache(it)
            }
        }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectDatabase.cachedProjectsDao().getBookmarkedProjects().toObservable().map {
            it.map {
                mapper.mapFromCache(it)
            }
        }

        }

    override fun setProjectAsBookmarked(projectId: String): Completable {
       return Completable.defer{
           projectDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
           Completable.complete()
       }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer{
            projectDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectDatabase.cachedProjectsDao().getProjects().isEmpty.map {
            !it
        }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer{
            projectDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCachedExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return projectDatabase.configDao().getConfig()
            .single(Config(lastCacheTime = 0))
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }
}