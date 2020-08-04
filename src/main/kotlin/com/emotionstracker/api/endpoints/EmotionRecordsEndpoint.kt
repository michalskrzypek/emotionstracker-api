package com.emotionstracker.api.endpoints

import com.emotionstracker.api.domain.EmotionRecord
import com.emotionstracker.api.service.EmotionRecordService
import org.springframework.http.MediaType

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/records")
class EmotionRecordsEndpoint(val emotionRecordService: EmotionRecordService) {

    @GetMapping(value = ["/{email}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRecordsForUser(@PathVariable email: String): ResponseEntity<List<EmotionRecord>> = ResponseEntity.ok(emotionRecordService.getAllForUser(email))
}
