package com.rj.books.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxUtils {
    companion object {
        fun <T> defaultTransformers(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.compose(schedulersTransformer())
            }
        }

        fun <T> schedulersTransformer(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io()).observeOn(
                    AndroidSchedulers.mainThread()
                )
            }
        }
    }
}