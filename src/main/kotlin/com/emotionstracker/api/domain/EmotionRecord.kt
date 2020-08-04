package com.emotionstracker.api.domain

import java.time.LocalDate
import javax.persistence.*

data class EmotionRecord(
        val count: Long,
        val date: LocalDate,
        @Enumerated(EnumType.STRING)
        val emotion: EmotionType,
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}
