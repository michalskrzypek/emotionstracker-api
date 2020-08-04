package com.emotionstracker.api.endpoints

import com.emotionstracker.api.importer.googledrive.GoogleDriveClient
import com.google.api.services.drive.model.File

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/files")
class DriveEndpoint(val googleDriveClient: GoogleDriveClient) {

    @GetMapping
    fun getFiles(): ResponseEntity<List<File>> = ResponseEntity.ok(googleDriveClient.listFiles())

    @GetMapping("/{fileId}")
    fun getFile(@PathVariable fileId: String): ResponseEntity<String> {
        return ResponseEntity.ok(googleDriveClient.getFile(fileId).toString())
    }
}
