package com.emotionstracker.api.service

import com.emotionstracker.api.domain.EmotionRecord
import com.emotionstracker.api.repository.EmotionRecordRepository
import org.springframework.stereotype.Service

@Service
class EmotionRecordService(val emotionRecordRepository: EmotionRecordRepository, val userService: UserService) {

    fun save(emotionRecord: EmotionRecord) = emotionRecordRepository.save(emotionRecord)

    fun getAllForUser(email: String): List<EmotionRecord> {
        val user = userService.getByEmail(email)
        return emotionRecordRepository.findAllByUser(user)
    }
}
