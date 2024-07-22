package com.newcentury99.p010_nc99_auth_server.commons.storage;

import com.newcentury99.p010_nc99_auth_server.library_temp.messages.SDKError;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileDirectoryServ {
    private final FileStorage storage;

    // 공통 - 특정 경로에 디렉터리 생성
    private void createDirectory(Path dirPath) throws IOException {
        if (!dirPath.toFile().mkdir()) {
            throw new IOException(SDKError.ERROR_REQUEST_FILE_WRITE_DIRECTORY_CREATE_FAILURE.getMessage());
        }
    }

    // 공통 - 디렉터리가 존재하지 않을 경우 디렉토리 생성
    private void createDirectoryIfNotExist(Path dirPath) throws IOException {
        if (!dirPath.toFile().isDirectory()) {
            this.createDirectory(dirPath);
        }
    }

    // 공통 - 엔티티 루트 디렉토리 생성
    private void createEntityRootStorageIfNotExist(String entityName) throws IOException {
        Path entityStorageRootPath = Paths.get(storage.getStorageRootPath(), entityName);
        this.createDirectoryIfNotExist(entityStorageRootPath);
    }

    // 공통 - 하위 엔티티 루트 디렉토리 생성
    private void createEntityRootStorageIfNotExist(Path parentEntityStoragePath, String subEntityName) throws IOException {
        Path subEntityStorageRootPath = Paths.get(parentEntityStoragePath.toString(), subEntityName);
        // Create Parent Dir If not Exists
        Files.createDirectories(subEntityStorageRootPath);
        this.createDirectoryIfNotExist(subEntityStorageRootPath);
    }

    // 공통 - 엔티티 개별 디렉토리 생성
    protected void createEntityStorage(String entityName, Long entityId) throws IOException {
        Path entityStoragePath = Paths.get(storage.getStorageRootPath(), entityName, entityName + "_" + entityId.toString());
        this.createEntityRootStorageIfNotExist(entityName);
        if (!entityStoragePath.toFile().isDirectory()) {
            this.createDirectory(entityStoragePath);
        }
    }

    // 공통 - 하위 엔티티 개별 디렉토리 생성
    protected void createEntityStorage(Path parentEntityStoragePath, String subEntity, Long subEntityId) throws IOException {
        Path subEntityStoragePath = Paths.get(parentEntityStoragePath.toString(), subEntity, subEntity + "_" + subEntityId);
        this.createEntityRootStorageIfNotExist(parentEntityStoragePath, subEntity);
        if (!subEntityStoragePath.toFile().isDirectory()) {
            this.createDirectory(subEntityStoragePath);
        }
    }

    // 공통 - 임시파일 스토리지 경로 조회
    protected Path getTempStoragePath() {
        return Paths.get(System.getProperty("java.io.tmpdir"));
    }

    // 공통 - 엔티티 스토리지 경로 조회
    protected Path getEntityStoragePath(String entityName, Long entityId) {
        return Paths.get(storage.getStorageRootPath(), entityName, entityName + "_" + entityId);
    }
    protected Path getEntityStoragePath(String entityName, UUID entityId) {
        return Paths.get(storage.getStorageRootPath(), entityName, entityName + "_" + entityId);
    }

    // 공통 - 하위 엔티티 스토리지 경로 조회
    protected Path getEntityStoragePath(Path parentEntityStoragePath, String subEntityName, Long subEntityId) {
        return Paths.get(parentEntityStoragePath.toString(), subEntityName, subEntityName + "_" + subEntityId);
    }
    protected Path getEntityStoragePath(Path parentEntityStoragePath, String subEntityName, UUID subEntityId) {
        return Paths.get(parentEntityStoragePath.toString(), subEntityName, subEntityName + "_" + subEntityId);
    }

    // 공통 - 엔티티 스토리지 삭제
    protected void deleteEntityStorage(String entityName, Long entityId) throws IOException {
        FileUtils.deleteDirectory(this.getEntityStoragePath(entityName, entityId).toFile());
    }
    protected void deleteEntityStorage(String entityName, UUID entityId) throws IOException {
        FileUtils.deleteDirectory(this.getEntityStoragePath(entityName, entityId).toFile());
    }

    // 공통 - 자식 엔티티 스토리지 삭제
    protected void deleteEntityStorage(Path parentEntityStoragePath, String subEntityName, Long subEntityId) throws IOException {
        FileUtils.deleteDirectory(this.getEntityStoragePath(parentEntityStoragePath, subEntityName, subEntityId).toFile());
    }
    protected void deleteEntityStorage(Path parentEntityStoragePath, String subEntityName, UUID subEntityId) throws IOException {
        FileUtils.deleteDirectory(this.getEntityStoragePath(parentEntityStoragePath, subEntityName, subEntityId).toFile());
    }
}
