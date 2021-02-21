package ng.com.mobile_ui.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ng.com.domain.executor.PostExecutionThread
import ng.com.mobile_ui.UiThread
import ng.com.mobile_ui.bookmark.BookmarkActivity
import ng.com.mobile_ui.browse.BrowseActivity

@Module
abstract class UiModule {

    @JvmSuppressWildcards
    @Provides
    fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkActivity
}