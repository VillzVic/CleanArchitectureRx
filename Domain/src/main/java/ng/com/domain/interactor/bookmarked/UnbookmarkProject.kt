package ng.com.domain.interactor.bookmarked

import io.reactivex.Completable
import ng.com.domain.dataAccessInterface.ProjectRepository
import ng.com.domain.executor.PostExecutionThread
import ng.com.domain.interactor.CompletableUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

open class UnbookmarkProject @Inject constructor(
    private val repository: @JvmSuppressWildcards ProjectRepository,
    postExecutionThread: @JvmSuppressWildcards PostExecutionThread
): CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread) {

    data class Params constructor(val projectId:String){
        companion object {
            fun forProject(projectId:String):Params{
                return Params(projectId)
            }
        }
    }

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if(params == null) throw IllegalArgumentException("Params cannot be null")

        return repository.unbookmarkProject(params.projectId)
    }


}