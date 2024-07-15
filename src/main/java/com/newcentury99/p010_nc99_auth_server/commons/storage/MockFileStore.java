package com.newcentury99.p010_nc99_auth_server.commons.storage;

import java.nio.file.FileStore;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;

public class MockFileStore extends FileStore {
    private final long totalCapacity;
    private final long remainCapacity;

    public MockFileStore(long totalCapacity, long remainCapacity) {
        this.totalCapacity = totalCapacity;
        this.remainCapacity = remainCapacity;
    }

    @Override
    public String name() {
        return "TEST";
    }

    @Override
    public String type() {
        return "MockFileStore";
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public long getTotalSpace() {
        return this.totalCapacity;
    }

    @Override
    public long getUsableSpace() {
        return this.remainCapacity;
    }

    @Override
    public long getUnallocatedSpace() {
        return 0;
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        return false;
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        return false;
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
        return null;
    }

    @Override
    public Object getAttribute(String attribute) {
        return null;
    }
}
