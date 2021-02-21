package ng.com.mobile_ui.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import ng.com.data.repository.ProjectsRemote
import ng.com.mobile_ui.BuildConfig
import ng.com.remote.ProjectsRemoteImpl
import ng.com.remote.service.GithubTrendingService
import ng.com.remote.service.GithubTrendingServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}