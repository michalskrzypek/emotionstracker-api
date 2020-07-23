package com.emotionstracker.api.importer.googledrive

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.DriveScopes
import org.springframework.stereotype.Service
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


@Service
class GoogleDriveAuthProvider {

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private val scopes = listOf(DriveScopes.DRIVE)
    private val credentialsFilePath = "/credentials.json"

    fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential =
            GoogleCredential
                    .fromStream(credentialsInputStream(), HTTP_TRANSPORT, JacksonFactory.getDefaultInstance())
                    .createScoped(scopes)

    @Throws(IOException::class)
    private fun credentialsInputStream(): InputStream = GoogleDriveAuthProvider::class.java.getResourceAsStream(credentialsFilePath)
            ?: throw FileNotFoundException("Resource not found: $credentialsFilePath")
}
