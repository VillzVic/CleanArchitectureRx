package ng.com.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ng.com.data.model.ProjectEntity
import ng.com.data.repository.ProjectsCache
import ng.com.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache
): ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
      return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }

    override fun clearProjects(): Completable {
       return projectsCache.clearProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
    }

}