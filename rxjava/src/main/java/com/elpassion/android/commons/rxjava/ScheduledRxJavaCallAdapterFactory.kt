package com.elpassion.android.commons.rxjava

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.Scheduler
import java.lang.reflect.Type

class ScheduledRxJavaCallAdapterFactory(private val observeOn: Scheduler,
                                        private val subscribeOn: Scheduler) : CallAdapter.Factory() {

    private val rxJavaCallAdapter by lazy { RxJavaCallAdapterFactory.createWithScheduler(subscribeOn) }

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*>? {
        if (getRawType(returnType) != Observable::class.java) {
            return null
        }
        @Suppress("UNCHECKED_CAST")
        val delegate = rxJavaCallAdapter.get(returnType, annotations, retrofit) as CallAdapter<Observable<*>>
        return object : CallAdapter<Observable<*>> {
            override fun <R : Any?> adapt(call: Call<R>?) = delegate.adapt(call).observeOn(observeOn)

            override fun responseType() = delegate.responseType()
        }
    }
}