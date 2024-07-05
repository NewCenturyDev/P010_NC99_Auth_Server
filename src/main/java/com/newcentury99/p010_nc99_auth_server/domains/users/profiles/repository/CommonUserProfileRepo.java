package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.repository;

import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.CommonUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommonUserProfileRepo extends JpaRepository<CommonUserProfile, UUID> {

}
