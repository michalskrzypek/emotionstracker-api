package com.emotionstracker.api.importer.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@ConditionalOnProperty(value = ["import.scheduling.enable"], havingValue = "true", matchIfMissing = true)
class SchedulingConfiguration
