package ng.com.domain.interactor.bookmarked

import io.reactivex.Observable
import ng.com.domain.dataAccessInterface.ProjectRepository
import ng.com.domain.executor.PostExecutionThread
import ng.com.domain.interactor.ObservableUseCase
import ng.com.domain.model.Project
import javax.inject.Inject

open class GetBookmarkedProject @Inject constructor(
    private val projectRepository: @JvmSuppressWildcards ProjectRepository,
    private val postExecutionThread: @JvmSuppressWildcards PostExecutionThread
):ObservableUseCase<List<Project>, Nothing>(postExecutionThread){


    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> = projectRepository.getBookMarkedProjects()


}