package ng.com.mobile_ui.injection

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import ng.com.domain.dataAccessInterface.ProjectRepository
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): ProjectRepository {
        return mock()
    }

}