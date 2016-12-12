package com.elpassion.android.commons.rxjava

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.http.GET
import rx.Observable
import rx.Scheduler
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers
import rx.schedulers.TestScheduler


class ScheduledRxJavaCallAdapterFactoryTest {

    @Rule
    @JvmField
    val server = MockWebServer()

    private val observeOn = TestScheduler()
    private val subscribeOn = TestScheduler()
    private val testSubscriber = TestSubscriber<String>()

    @Before
    fun setUp() {
        server.enqueue(MockResponse().setBody("Hi"))
    }

    @Test
    fun shouldObserveOnProperScheduler() {
        val service = createRetrofit(observeOn, Schedulers.immediate()).create(Service::class.java)
        service.call().subscribe(testSubscriber)

        testSubscriber.assertNoValues()
        observeOn.triggerActions()

        testSubscriber.apply {
            assertValueCount(1)
            assertCompleted()
        }
    }

    @Test
    fun shouldSubscribeOnProperScheduler() {
        val service = createRetrofit(Schedulers.immediate(), subscribeOn).create(Service::class.java)
        service.call().subscribe(testSubscriber)

        testSubscriber.assertNoValues()
        subscribeOn.triggerActions()

        testSubscriber.apply {
            assertValueCount(1)
            assertCompleted()
        }
    }

    interface Service {
        @GET("/")
        fun call(): Observable<String>
    }

    private fun createRetrofit(observeOn: Scheduler, subscribeOn: Scheduler) = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(StringConverterFactory())
            .addCallAdapterFactory(ScheduledRxJavaCallAdapterFactory(observeOn, subscribeOn))
            .build()
}