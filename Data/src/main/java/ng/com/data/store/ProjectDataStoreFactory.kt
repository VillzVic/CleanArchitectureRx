package ng.com.data.store

import ng.com.data.repository.ProjectsDataStore
import javax.inject.Inject

//accessing which datastore to use
open class ProjectDataStoreFactory @Inject constructor(
    private val projectCacheDataStore: ProjectCacheDataStore,
    private val projectRemoteDataStore: ProjectRemoteDataStore
){

    open fun getDataStore(projectCached: Boolean, cachedExpired: Boolean) : ProjectsDataStore {
        return if(projectCached && !cachedExpired){
            projectCacheDataStore
        } else {
            projectRemoteDataStore
        }
    }

    open fun getCacheDataStore() = projectCacheDataStore
}