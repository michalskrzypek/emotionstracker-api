package com.emotionstracker.api.repository

import com.emotionstracker.api.domain.EmotionRecord
import com.emotionstracker.api.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmotionRecordRepository : JpaRepository<EmotionRecord, Long> {
    fun findAllByUser(user: User): List<EmotionRecord>
}
