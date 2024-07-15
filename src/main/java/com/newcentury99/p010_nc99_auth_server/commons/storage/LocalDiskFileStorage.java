package com.newcentury99.p010_nc99_auth_server.commons.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class LocalDiskFileStorage implements FileStorage {
    /* **************************** 변경 금지 구역 ****************************** */
    // IMPORTANT: storageRootPath 는 환경 변수별로 분리된 application.properties 에 기록합니다. (storage.rootPath 값)

    private Path storageRootPath;
    private FileStore storage;
    /* ************************************************************************** */


    @Override
    public String getStorageRootPath() {
        return this.storageRootPath.toString();
    }

    @Value("${storage.rootPath}")
    public void setStorageRootPath(String value) throws IOException {
        if (value != null) {
            this.storageRootPath = new File(value).toPath();
            this.storage = Files.getFileStore(this.storageRootPath);
        } else {
            this.storageRootPath = Path.of("/temp");
            this.storage = new MockFileStore(1024 * 1024 * 1024, 500 * 1024 * 1024);
        }
    }

    public Long getStorageTotalCapacity() throws IOException {
        return this.storage.getTotalSpace();
    }

    public String getStorageTotalCapacity(String unit) throws IOException {
        return formatCapacityUnit(this.storage.getTotalSpace(), unit);
    }

    public Long getStorageRemainCapacity() throws IOException {
        return this.storage.getUsableSpace();
    }

    public String getStorageRemainCapacity(String unit) throws IOException {
        return formatCapacityUnit(this.storage.getUsableSpace(), unit);
    }
    
    // 용량 단위표기 설정 유틸리티 메소드
    private static String formatCapacityUnit(long bytes, String unit) {
        List<String> units = List.of("B", "KB", "MB", "GB", "TB");
        int unitIndex = units.indexOf(unit);

        double size = bytes;
        for (int i = 0; i < unitIndex; i++) {
            size /= 1024;
        }
        return unitIndex > 2 ? String.format("%.2f %s", size, units.get(unitIndex)) : String.format("%.1f %s", size, units.get(unitIndex));
    }
}
