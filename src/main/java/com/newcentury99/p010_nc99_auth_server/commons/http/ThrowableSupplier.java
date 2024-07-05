package com.newcentury99.p010_nc99_auth_server.commons.http;

public interface ThrowableSupplier<T> {
    T get() throws Exception;
}
