package ng.com.domain.dataAccessInterface

import io.reactivex.Completable
import io.reactivex.Observable
import ng.com.domain.model.Project

//for data layer to conform to
interface ProjectRepository {
    fun getProjects() : Observable<List<Project>>

    fun bookmarkProject(projectId:String): Completable

    fun unbookmarkProject(projectId:String): Completable

    fun getBookMarkedProjects(): Observable<List<Project>>
}