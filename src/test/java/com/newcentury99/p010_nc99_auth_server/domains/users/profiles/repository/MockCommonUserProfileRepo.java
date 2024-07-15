package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.repository;

import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.CommonUserProfile;
import lombok.NonNull;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class MockCommonUserProfileRepo implements CommonUserProfileRepo {
    @Override
    @NonNull
    public <S extends CommonUserProfile> S save(@NonNull S entity) {
        return entity;
    }

    @Override
    public void flush() {

    }

    @Override
    @NonNull
    public <S extends CommonUserProfile> S saveAndFlush(@NonNull S entity) {
        return entity;
    }

    @Override
    @NonNull
    public <S extends CommonUserProfile> List<S> saveAllAndFlush(@NonNull Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(@NonNull Iterable<CommonUserProfile> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(@NonNull Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @NonNull
    @Override
    public CommonUserProfile getOne(@NonNull UUID uuid) {
        return new CommonUserProfile();
    }

    @NonNull
    @Override
    public CommonUserProfile getById(@NonNull UUID uuid) {
        return new CommonUserProfile();
    }

    @NonNull
    @Override
    public CommonUserProfile getReferenceById(@NonNull UUID uuid) {
        return new CommonUserProfile();
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile> Optional<S> findOne(@NonNull Example<S> example) {
        return Optional.empty();
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile> List<S> findAll(@NonNull Example<S> example) {
        return List.of();
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile> List<S> findAll(@NonNull Example<S> example, Sort sort) {
        return List.of();
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile> Page<S> findAll(@NonNull Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CommonUserProfile> long count(@NonNull Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CommonUserProfile> boolean exists(@NonNull Example<S> example) {
        return false;
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile, R> R findBy(@NonNull Example<S> example, @NonNull Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @NonNull
    @Override
    public <S extends CommonUserProfile> List<S> saveAll(@NonNull Iterable<S> entities) {
        return List.of();
    }

    @NonNull
    @Override
    public Optional<CommonUserProfile> findById(@NonNull UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(@NonNull UUID uuid) {
        return false;
    }

    @NonNull
    @Override
    public List<CommonUserProfile> findAll() {
        return List.of();
    }

    @NonNull
    @Override
    public List<CommonUserProfile> findAllById(@NonNull Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(@NonNull UUID uuid) {

    }

    @Override
    public void delete(@NonNull CommonUserProfile entity) {

    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends CommonUserProfile> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @NonNull
    @Override
    public List<CommonUserProfile> findAll(@NonNull Sort sort) {
        return List.of();
    }

    @NonNull
    @Override
    public Page<CommonUserProfile> findAll(@NonNull Pageable pageable) {
        return null;
    }
}
