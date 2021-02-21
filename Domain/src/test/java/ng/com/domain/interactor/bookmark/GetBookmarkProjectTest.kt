package ng.com.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import ng.com.domain.dataAccessInterface.ProjectRepository
import ng.com.domain.executor.PostExecutionThread
import ng.com.domain.interactor.bookmarked.GetBookmarkedProject
import ng.com.domain.model.Project
import ng.com.domain.test.ProjectDataFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkProjectTest {

    private lateinit var getBookmarkedProject: GetBookmarkedProject
    @Mock private lateinit var projectRepository: ProjectRepository
    @Mock private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getBookmarkedProject = GetBookmarkedProject(projectRepository,postExecutionThread)
    }

    @Test
    fun getBookmarkProjectCompletes(){
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getBookmarkedProject.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkProjectReturns(){
        val expectedValue = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(expectedValue))

        val testObserver = getBookmarkedProject.buildUseCaseObservable().test() //allows us to perform checks on the observer for testing
        testObserver.assertValue(expectedValue)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>){
        whenever(projectRepository.getBookMarkedProjects())
            .thenReturn(observable)
    }
}