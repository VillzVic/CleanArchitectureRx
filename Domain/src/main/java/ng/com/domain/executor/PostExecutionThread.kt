package ng.com.domain.executor

import io.reactivex.Scheduler

//create an abstraction for other layers to provide AndroidScheduler.MainThread because Domain layer should know nothing about android framework
interface PostExecutionThread {
    val scheduler : Scheduler
}