package ng.com.mobile_ui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ng.com.domain.dataAccessInterface.ProjectRepository
import ng.com.mobile_ui.injection.module.PresentationModule
import ng.com.mobile_ui.injection.module.UiModule
import ng.com.mobile_ui.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UiModule::class,
    TestRemoteModule::class))
interface TestApplicationComponent {

    fun projectsRepository(): ProjectRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}