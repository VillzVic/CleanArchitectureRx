package ng.com.mobile_ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ng.com.domain.executor.PostExecutionThread
import javax.inject.Inject

open class UiThread @Inject constructor(): PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}