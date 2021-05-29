package org.skyfaced.todi.models.response

import org.skyfaced.todi.enums.ResponseMessageType

data class ResponseMessage(
    val type: ResponseMessageType,
    val message: String,
    val stackTrace: String? = null
)