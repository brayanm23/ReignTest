package com.test.reign.core

import com.test.reign.core.Status.LOADING
import com.test.reign.core.Status.SERVER_ERROR
import com.test.reign.core.Status.CONNECTION_ERROR
import com.test.reign.core.Status.SUCCESS

data class Result<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> loading(data: T? = null): Result<T> = Result(LOADING, data)
        fun <T> success(data: T?): Result<T> = Result(SUCCESS, data)
        fun <T> serverError(data: T? = null): Result<T> = Result(SERVER_ERROR, data)
        fun <T> connectionError(data: T? = null): Result<T> = Result(CONNECTION_ERROR, data)
    }
}
