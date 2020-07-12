package com.emotionstracker.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmotionsTrackerApiApplication

fun main(args: Array<String>) {
	runApplication<EmotionsTrackerApiApplication>(*args)
}
