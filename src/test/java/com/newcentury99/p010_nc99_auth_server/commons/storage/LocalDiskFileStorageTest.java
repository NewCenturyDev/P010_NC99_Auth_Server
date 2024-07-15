package com.newcentury99.p010_nc99_auth_server.commons.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalDiskFileStorageTest {
    private LocalDiskFileStorage sampleStorage;

    @BeforeEach
    void setupSampleStorage() throws IOException {
        sampleStorage = new LocalDiskFileStorage();
        sampleStorage.setStorageRootPath(null);
    }

    @Test
    void getStorageRootPathTest() {
        // Given
        String sampleRootPath = System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win") ?
                "\\temp" : "/temp";

        // When
        String rootPath = sampleStorage.getStorageRootPath();

        // Then
        assertNotNull(rootPath);
        assertEquals(sampleRootPath, rootPath);
    }

    @Test
    void setStorageRootPathTest() {
        //Given
        String sampleRootPath = System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win") ?
                "\\temp" : "/temp";

        // When & Then
        assertDoesNotThrow(() -> sampleStorage.setStorageRootPath(null));
        assertEquals(sampleRootPath, sampleStorage.getStorageRootPath());
    }

    @Test
    void getStorageTotalCapacityTest() {
        // Given
        long sampleCapacity = 1024L * 1024 * 1024;

        // When & Then
        assertDoesNotThrow(() -> assertEquals(sampleCapacity, sampleStorage.getStorageTotalCapacity()));
    }

    @Test
    void getStorageTotalCapacityWithFormatTest() {
        // Given
        String sampleCapacityWithUnit = "1.00 GB";

        // When & Then
        assertDoesNotThrow(() -> assertEquals(sampleCapacityWithUnit, sampleStorage.getStorageTotalCapacity("GB")));
    }

    @Test
    void getStorageRemainCapacityTest() {
        long sampleCapacity = 500L * 1024 * 1024;

        // When & Then
        assertDoesNotThrow(() -> assertEquals(sampleCapacity, sampleStorage.getStorageRemainCapacity()));
    }

    @Test
    void getStorageRemainCapacityWithFormatTest() {
        String sampleCapacityWithUnit = "500.0 MB";

        // When & Then
        assertDoesNotThrow(() -> assertEquals(sampleCapacityWithUnit, sampleStorage.getStorageRemainCapacity("MB")));
    }
}
