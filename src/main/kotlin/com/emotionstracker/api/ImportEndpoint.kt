package com.emotionstracker.api

import com.emotionstracker.api.importer.ImportScheduler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/import")
class ImportEndpoint(val importScheduler: ImportScheduler) {

    @GetMapping("/trigger")
    fun triggerImport(): ResponseEntity<String> {
        importScheduler.importWithSchedule()
        return ResponseEntity.ok("import successful")
    }
}
