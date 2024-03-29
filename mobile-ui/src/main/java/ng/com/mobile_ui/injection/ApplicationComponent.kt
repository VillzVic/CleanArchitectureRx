package ng.com.mobile_ui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ng.com.mobile_ui.GithubTrendingApplication
import ng.com.mobile_ui.injection.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, UiModule::class, PresentationModule::class, DataModule::class, CacheModule::class, RemoteModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GithubTrendingApplication)

}