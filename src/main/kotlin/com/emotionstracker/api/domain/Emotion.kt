package com.emotionstracker.api.domain

import java.time.LocalDate
import javax.persistence.*

data class Emotion(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val count: Long,

        val date: LocalDate,

        @Enumerated(EnumType.STRING)
        val emotion: EmotionType,

        @OneToOne
        val user: User
)
