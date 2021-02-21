package ng.com.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ng.com.domain.executor.PostExecutionThread

// Base usecase class for returning observables and executing observables ... can be converted to flow later
abstract class ObservableUseCase<T, in Params> constructor(
    private val postExecutionThread: @JvmSuppressWildcards PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    //create observable
    protected abstract fun buildUseCaseObservable(params: Params? =null) :Observable<T>

    //execute the observable
    open fun execute(observer: DisposableObserver<T>, params: Params? = null){
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        disposables.dispose()
    }
    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }
}