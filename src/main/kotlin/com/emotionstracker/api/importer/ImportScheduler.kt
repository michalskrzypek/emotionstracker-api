package com.emotionstracker.api.importer

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ImportScheduler(private val importers: List<Importer>) {
    @Scheduled(cron = "0 45 13 * * *")
    fun importWithSchedule() = importers.forEach { it.import() }
}
