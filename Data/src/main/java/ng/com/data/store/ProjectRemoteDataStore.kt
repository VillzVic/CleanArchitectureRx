package ng.com.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ng.com.data.model.ProjectEntity
import ng.com.data.repository.ProjectsDataStore
import ng.com.data.repository.ProjectsRemote
import java.lang.UnsupportedOperationException
import javax.inject.Inject

open class ProjectRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote
) : ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getprojects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("This operation is not supported")
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("This operation is not supported")
    }
}