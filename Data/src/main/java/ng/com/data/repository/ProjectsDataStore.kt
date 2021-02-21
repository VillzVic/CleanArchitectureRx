package ng.com.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ng.com.data.model.ProjectEntity

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId:String): Completable

    fun setProjectAsNotBookmarked(projectId:String): Completable

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable
}