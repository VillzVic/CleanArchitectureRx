package ng.com.mobile_ui.injection.module

import dagger.Binds
import dagger.Module
import ng.com.data.ProjectsDataRepository
import ng.com.domain.dataAccessInterface.ProjectRepository

@Module
abstract class DataModule {


    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectRepository
}