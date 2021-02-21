package ng.com.mobile_ui.injection

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import ng.com.data.repository.ProjectsRemote
import ng.com.remote.service.GithubTrendingService
import org.mockito.Mockito.mock

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }

}