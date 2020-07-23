package com.emotionstracker.api

import com.emotionstracker.api.importer.googledrive.GoogleDriveClient
import com.google.api.services.drive.model.File

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/files")
class DriveEndpoint(val googleDriveClient: GoogleDriveClient) {

    @GetMapping
    fun getFiles(): ResponseEntity<List<File>> = ResponseEntity.ok(googleDriveClient.listFiles())

    @GetMapping("/add")
    fun createFile(): ResponseEntity<String> {
        googleDriveClient.addFile()
        return ResponseEntity.ok("success")
    }
}
