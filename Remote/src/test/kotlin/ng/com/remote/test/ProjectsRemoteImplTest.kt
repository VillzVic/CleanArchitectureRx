package ng.com.remote.test

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import ng.com.data.model.ProjectEntity
import ng.com.remote.ProjectsRemoteImpl
import ng.com.remote.mapper.ProjectResponseModelMapper
import ng.com.remote.model.ProjectModel
import ng.com.remote.model.ProjectsResponseModel
import ng.com.remote.service.GithubTrendingService
import ng.com.remote.test.factory.ProjectDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service,mapper)

    @Test
    fun getProjectsCompletes(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getprojects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getprojects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData(){
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepository(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()

        response.items.forEach{
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectResponseMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getprojects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters(){
        stubGithubTrendingServiceSearchRepository(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getprojects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }


    private fun stubGithubTrendingServiceSearchRepository(observable: Observable<ProjectsResponseModel>){

        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectResponseMapperMapFromModel(model: ProjectModel, entity: ProjectEntity){

        whenever(mapper.mapFromModel(model)).thenReturn(entity)
    }

}