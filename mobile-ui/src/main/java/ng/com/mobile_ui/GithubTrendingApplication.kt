package ng.com.mobile_ui

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ng.com.mobile_ui.injection.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class GithubTrendingApplication: Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }
}