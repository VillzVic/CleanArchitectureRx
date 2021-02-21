package ng.com.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import ng.com.domain.interactor.bookmarked.BookmarkProject
import ng.com.domain.interactor.bookmarked.UnbookmarkProject
import ng.com.domain.interactor.browse.GetProjects
import ng.com.domain.model.Project
import ng.com.presentation.mapper.ProjectViewMapper
import ng.com.presentation.model.ProjectView
import ng.com.presentation.state.Resource
import ng.com.presentation.state.ResourceState
import javax.inject.Inject

open class BrowseProjectsViewModel @Inject constructor(
    private val getProject: GetProjects,
    private val bookmarkProject: BookmarkProject,
    private val unbookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()


    fun getProjects(): LiveData<Resource<List<ProjectView>>> = liveData


    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProject.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId:String){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProject.execute(BookMarkProjectSubscriber(), BookmarkProject.Params(projectId))
    }

    fun unbookmarkProject(projectId:String){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unbookmarkProject.execute(BookMarkProjectSubscriber(),UnbookmarkProject.Params(projectId))
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>(){
        override fun onComplete() {

        }

        override fun onNext(value: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, value.map {
                mapper.mapToView(it)
            }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e?.localizedMessage))
        }



    }

    inner class BookMarkProjectSubscriber :  DisposableCompletableObserver(){
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS,liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data, e?.localizedMessage))
        }

    }


    override fun onCleared() {
        getProject.dispose()
        super.onCleared()
    }
}