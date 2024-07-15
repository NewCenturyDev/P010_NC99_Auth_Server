package com.newcentury99.p010_nc99_auth_server.commons.storage;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileDeleteServTest {
    private final LocalDiskFileStorage sampleStorage;
    private final FileDeleteServ fileDeleteServ;

    public FileDeleteServTest() throws IOException {
        this.sampleStorage = new LocalDiskFileStorage();
        sampleStorage.setStorageRootPath(Files.temporaryFolderPath());
        this.fileDeleteServ = new FileDeleteServ(sampleStorage);
    }

    @Test
    void deleteFileTest() {
        // Given
        String sampleFileName = "test.txt";
        Path sampleFilePath = Paths.get(this.sampleStorage.getStorageRootPath(), sampleFileName);
        Files.newFile(sampleFilePath.toString());

        // When & Then
        assertDoesNotThrow(() -> fileDeleteServ.deleteFile(sampleFileName));
        assertFalse(sampleFilePath.toFile().isFile());
    }
}
