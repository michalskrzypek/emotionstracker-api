package com.emotionstracker.api.domain

import javax.persistence.*

@Entity
data class User(
        @Column(nullable = false)
        val email: String
) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L
}
