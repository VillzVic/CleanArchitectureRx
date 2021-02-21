package ng.com.data.repository

import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import ng.com.data.mapper.EntityMapper
import ng.com.data.model.ProjectEntity

//for remote layer to conform to
interface ProjectsRemote {

    fun getprojects(): Observable<List<ProjectEntity>>
}