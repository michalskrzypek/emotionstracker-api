package com.emotionstracker.api.logging

import org.slf4j.LoggerFactory

object LoggerUtil {
    fun <T> logger(clazz: Class<T>) = LoggerFactory.getLogger(clazz)
}
