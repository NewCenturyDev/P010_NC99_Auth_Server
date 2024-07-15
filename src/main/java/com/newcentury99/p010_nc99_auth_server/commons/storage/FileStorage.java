package com.newcentury99.p010_nc99_auth_server.commons.storage;

import com.newcentury99.p010_nc99_auth_server.library_temp.LibraryAPI;

import java.io.IOException;

public interface FileStorage {
    @LibraryAPI
    String getStorageRootPath();
    @LibraryAPI
    void setStorageRootPath(String value) throws IOException;
    @LibraryAPI
    Long getStorageTotalCapacity() throws IOException;
    @LibraryAPI
    String getStorageTotalCapacity(String unit) throws IOException;
    @LibraryAPI
    Long getStorageRemainCapacity() throws IOException;
    @LibraryAPI
    String getStorageRemainCapacity(String unit) throws IOException;
}
