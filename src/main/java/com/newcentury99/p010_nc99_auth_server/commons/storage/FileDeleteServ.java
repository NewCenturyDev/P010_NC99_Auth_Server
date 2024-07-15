package com.newcentury99.p010_nc99_auth_server.commons.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class FileDeleteServ {
    private final FileStorage storage;

    protected void deleteFile(String url) throws IOException {
        String osName = System.getProperty("os.name").toLowerCase();

        // 파일 삭제
        Path savePath;
        if (osName.contains("win")) {
            savePath = Paths.get(storage.getStorageRootPath(), url.replace("/storage", "").replace("/", "\\"));
        } else {
            savePath = Paths.get(storage.getStorageRootPath(), url.replace("/storage", ""));
        }
        Files.deleteIfExists(savePath);
    }
}
