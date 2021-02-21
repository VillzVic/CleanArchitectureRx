package ng.com.domain.interactor.browse

import io.reactivex.Observable
import ng.com.domain.dataAccessInterface.ProjectRepository
import ng.com.domain.executor.PostExecutionThread
import ng.com.domain.interactor.ObservableUseCase
import ng.com.domain.model.Project
import javax.inject.Inject


open class GetProjects @Inject constructor(
    private val projectRepository:  ProjectRepository,
    postExecutionThread: @JvmSuppressWildcards PostExecutionThread
): ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> = projectRepository.getProjects()




}