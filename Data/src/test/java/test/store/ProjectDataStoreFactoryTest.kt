package test.store

import com.nhaarman.mockito_kotlin.mock
import ng.com.data.ProjectsDataRepository
import ng.com.data.store.ProjectCacheDataStore
import ng.com.data.store.ProjectDataStoreFactory
import ng.com.data.store.ProjectRemoteDataStore
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDataStoreFactoryTest {

    private val cacheStore = mock<ProjectCacheDataStore>()
    private val remoteStore = mock<ProjectRemoteDataStore>()
    private lateinit var factory: ProjectDataStoreFactory

    @Before
    fun setUp() {
        factory = ProjectDataStoreFactory(cacheStore,remoteStore)
    }

    @Test
    fun getDataStoreReturnsRemoteWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(projectCached = true, cachedExpired = true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCache() {
        assertEquals(remoteStore, factory.getDataStore(projectCached = false, cachedExpired = false))
    }

    @Test
    fun getDataStoreReturnsCacheStore(){
        assertEquals(cacheStore, factory.getDataStore(projectCached = true, cachedExpired = false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore(){
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}