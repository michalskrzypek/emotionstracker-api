package com.emotionstracker.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val HEALTH_CHECK_ENDPOINT: String = "/healthCheck"

@RestController
@RequestMapping(HEALTH_CHECK_ENDPOINT)
class HealthCheckEndpoint {

    @GetMapping(produces = ["text/json"])
    fun healthCheck(): ResponseEntity<HealthCheckResponse> = ResponseEntity.ok(HealthCheckResponse(HealthCheckStatus.OK))

    data class HealthCheckResponse(val status: HealthCheckStatus)

    enum class HealthCheckStatus(status: String) {
        OK("OK"), FAILED("Fail")
    }
}
