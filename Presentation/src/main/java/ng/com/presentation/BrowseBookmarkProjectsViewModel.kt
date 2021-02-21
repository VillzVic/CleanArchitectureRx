package ng.com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ng.com.domain.interactor.bookmarked.GetBookmarkedProject
import ng.com.domain.model.Project
import ng.com.presentation.mapper.ProjectViewMapper
import ng.com.presentation.model.ProjectView
import ng.com.presentation.state.Resource
import ng.com.presentation.state.ResourceState
import javax.inject.Inject

open class BrowseBookmarkProjectsViewModel @Inject constructor(
    private val getBookmarkedProject: GetBookmarkedProject,
    private val mapper: ProjectViewMapper
):ViewModel(){

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    fun getProjects(): LiveData<Resource<List<ProjectView>>> = liveData

    fun fetchProjects(){
        getBookmarkedProject.execute(BookMarkSubscriber())
    }

    inner class BookMarkSubscriber(): DisposableObserver<List<Project>>(){
        override fun onComplete() {

        }

        override fun onNext(value: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, value.map {
                mapper.mapToView(it)
            }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, null))
        }
    }

    override fun onCleared() {
        getBookmarkedProject.dispose()
        super.onCleared()
    }
}