package com.omarmouki.chucknorris.core.utils

sealed class CommonResponse<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : CommonResponse<T>(data)
    class Success<T>(data: T) : CommonResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : CommonResponse<T>(data, message)

}
