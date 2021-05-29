package org.skyfaced.todi.models.response

import org.skyfaced.todi.enums.ResponseMessageType

sealed class Response<T>(val message: ResponseMessage?, val data: T?) {
    class Success<T>(message: String?, data: T?) : Response<T>(
        message = message?.let { ResponseMessage(ResponseMessageType.Success, message, null) },
        data = data
    )

    class Error(message: String?, stackTrace: String?) : Response<Nothing>(
        message = message?.let { ResponseMessage(ResponseMessageType.Error, message, stackTrace) },
        data = null
    )

    class Warning<T>(message: String?, data: T?) : Response<T>(
        message = message?.let { ResponseMessage(ResponseMessageType.Warning, message, null) },
        data = data
    )
}
