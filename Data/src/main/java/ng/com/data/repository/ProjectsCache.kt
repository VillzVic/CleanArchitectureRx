package ng.com.data.repository

import com.sun.org.apache.xpath.internal.operations.Bool
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ng.com.data.model.ProjectEntity
import ng.com.domain.model.Project

//for cache layer to conform to
interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId:String): Completable

    fun setProjectAsNotBookmarked(projectId:String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long) : Completable

    fun isProjectsCachedExpired() : Single<Boolean>
}