package com.example.rxjava

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(shouldDelayErrors: Boolean = false) =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), shouldDelayErrors)