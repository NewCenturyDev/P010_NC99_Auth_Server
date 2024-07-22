package com.newcentury99.p010_nc99_auth_server.commons.storage;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileDirectoryServTest {
    private final LocalDiskFileStorage sampleStorage;
    private final FileDirectoryServ fileDirectoryServ;

    public FileDirectoryServTest() throws IOException {
        this.sampleStorage = new LocalDiskFileStorage();
        sampleStorage.setStorageRootPath(null);
        this.fileDirectoryServ = new FileDirectoryServ(sampleStorage);
    }

    @AfterEach
    void resetTempDir() throws IOException {
        FileUtils.cleanDirectory(Paths.get(sampleStorage.getStorageRootPath()).toFile());
    }

    @Test
    void createEntityStorageTest() {
        // Given
        String sampleEntityName = "testDir";
        Long sampleEntitySeqID = 1L;
        Path sampleDirPath = Paths.get(this.sampleStorage.getStorageRootPath(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqID);

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.createEntityStorage(sampleEntityName, sampleEntitySeqID));
        assertTrue(sampleDirPath.toFile().isDirectory());
    }

    @Test
    void createEntityStorageTest2() {
        // Given
        Path sampleParentEntityPath = Paths.get(this.sampleStorage.getStorageRootPath(), "parent");
        String sampleEntityName = "child";
        Long sampleEntitySeqID = 1L;
        Path sampleDirPath = Paths.get(sampleParentEntityPath.toString(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqID);

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.createEntityStorage(sampleParentEntityPath, sampleEntityName, sampleEntitySeqID));
        assertTrue(sampleDirPath.toFile().isDirectory());
    }

    @Test
    void getTempStoragePathTest() {
        // Given
        Path tempStoragePathSample = Path.of(System.getProperty("java.io.tmpdir"));

        // When
        Path tempStoragePath = fileDirectoryServ.getTempStoragePath();

        // Then
        assertEquals(tempStoragePathSample, tempStoragePath);
    }

    @Test
    void getEntityStoragePathWithSeqIDTest() throws IOException {
        // Given
        String sampleEntityName = "testDir";
        Long sampleEntitySeqID = 1L;
        Path sampleDirPath = Paths.get(this.sampleStorage.getStorageRootPath(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When
        Path dir = fileDirectoryServ.getEntityStoragePath(sampleEntityName, sampleEntitySeqID);

        // Then
        assertTrue(sampleDirPath.toFile().isDirectory());
        assertEquals(sampleDirPath.toString(), dir.toString());
    }

    @Test
    void getEntityStoragePathWithSeqIDTest2() throws IOException {
        // Given
        Path sampleParentEntityPath = Paths.get(this.sampleStorage.getStorageRootPath(), "parent");
        String sampleEntityName = "child";
        Long sampleEntitySeqID = 1L;
        Path sampleDirPath = Paths.get(sampleParentEntityPath.toString(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When
        Path dir = fileDirectoryServ.getEntityStoragePath(sampleParentEntityPath, sampleEntityName, sampleEntitySeqID);

        // Then
        assertTrue(sampleDirPath.toFile().isDirectory());
        assertEquals(sampleDirPath.toString(), dir.toString());
    }

    @Test
    void getEntityStoragePathWithUUIDTest() throws IOException {
        // Given
        String sampleEntityName = "testDir";
        UUID sampleEntitySeqUUID = UUID.randomUUID();
        Path sampleDirPath = Paths.get(this.sampleStorage.getStorageRootPath(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqUUID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When
        Path dir = fileDirectoryServ.getEntityStoragePath(sampleEntityName, sampleEntitySeqUUID);

        // Then
        assertTrue(sampleDirPath.toFile().isDirectory());
        assertEquals(sampleDirPath.toString(), dir.toString());
    }

    @Test
    void getEntityStoragePathWithUUIDTest2() throws IOException {
        // Given
        Path sampleParentEntityPath = Paths.get(this.sampleStorage.getStorageRootPath(), "parent");
        String sampleEntityName = "child";
        UUID sampleEntitySeqID = UUID.randomUUID();
        Path sampleDirPath = Paths.get(sampleParentEntityPath.toString(), sampleEntityName, sampleEntityName + "_" + sampleEntitySeqID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When
        Path dir = fileDirectoryServ.getEntityStoragePath(sampleParentEntityPath, sampleEntityName, sampleEntitySeqID);

        // Then
        assertTrue(sampleDirPath.toFile().isDirectory());
        assertEquals(sampleDirPath.toString(), dir.toString());
    }

    @Test
    void deleteEntityStorageWithSeqIDTest() throws IOException {
        // Given
        String sampleEntityName = "testDir";
        Long sampleEntityID = 1L;
        Path sampleDirPath = Paths.get(this.sampleStorage.getStorageRootPath(), "testDir", sampleEntityName + "_" + sampleEntityID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.deleteEntityStorage(sampleEntityName, sampleEntityID));
        assertFalse(sampleDirPath.toFile().isDirectory());
    }

    @Test
    void deleteEntityStorageWithSeqIDTest2() throws IOException {
        // Given
        Path sampleParentEntityPath = Paths.get(this.sampleStorage.getStorageRootPath(), "parent");
        String sampleEntityName = "child";
        Long sampleEntityID = 1L;
        Path sampleDirPath = Paths.get(sampleParentEntityPath.toString(), "child", sampleEntityName + "_" + sampleEntityID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.deleteEntityStorage(sampleParentEntityPath, sampleEntityName, sampleEntityID));
        assertFalse(sampleDirPath.toFile().isDirectory());
    }

    @Test
    void deleteEntityStorageWithUUIDTest() throws IOException {
        // Given
        String sampleEntityName = "testDir";
        UUID sampleEntityID = UUID.randomUUID();
        Path sampleDirPath = Paths.get(this.sampleStorage.getStorageRootPath(), "testDir", sampleEntityName + "_" + sampleEntityID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.deleteEntityStorage(sampleEntityName, sampleEntityID));
        assertFalse(sampleDirPath.toFile().isDirectory());
    }

    @Test
    void deleteEntityStorageWithUUIDTest2() throws IOException {
        // Given
        Path sampleParentEntityPath = Paths.get(this.sampleStorage.getStorageRootPath(), "parent");
        String sampleEntityName = "child";
        UUID sampleEntityID = UUID.randomUUID();
        Path sampleDirPath = Paths.get(sampleParentEntityPath.toString(), "child", sampleEntityName + "_" + sampleEntityID);
        FileUtils.forceMkdir(sampleDirPath.toFile());

        // When & Then
        assertDoesNotThrow(() -> fileDirectoryServ.deleteEntityStorage(sampleParentEntityPath, sampleEntityName, sampleEntityID));
        assertFalse(sampleDirPath.toFile().isDirectory());
    }
}
