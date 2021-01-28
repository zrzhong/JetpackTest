package com.zzr.jetpacktest.kotlin_test

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/23
 */
class MyContinuationInterceptor:ContinuationInterceptor {
    override val key: CoroutineContext.Key<*> = ContinuationInterceptor


    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }
}