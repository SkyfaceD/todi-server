package org.skyfaced.todi.models

interface ReceiveValidator {
    fun <T> validate(): T
}