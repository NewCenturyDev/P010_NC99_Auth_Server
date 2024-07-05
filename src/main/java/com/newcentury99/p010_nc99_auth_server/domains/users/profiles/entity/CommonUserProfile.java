package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.uuid.UuidCreator;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.converters.PermissionScopeConverter;
import com.newcentury99.p010_nc99_auth_server.library_temp.security.PermissionScope;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserProfile implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", columnDefinition = "varchar")
    private UUID id;

    /* User Information (Essential) */
    // 사용자 이메일 (직접 로그인시 사용)
    @Column(unique = true, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    // 사용자 비밀번호
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 사용자 언어설정
    @Column(nullable = false)
    private Locale language;

    // 사용자 닉네임 (생성시 공란은 허용하나, 기본생성로직)
    @Column(nullable = false)
    private String username;

    /* User Information (Optional) */
    // 실명
    @Column
    private String name;

    // 국적
    @Column
    private Locale country;

    // 생년월일
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birth;

    // 사용자 성별
    @Column
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    // 사용자 전화번호
    @Column
    @Embedded
    private UserPhone phone;

    /* 계정 생성 일시 및 마지막 로그인 일시 (익명 계쩡 및 휴면처리 참고 - 추후 자동화) */
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    @Column
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    @Column
    private LocalDateTime lastLoginAt;

    /* API 및 마이크로서비스 접근 권한 Scope */
    @Builder.Default
    @Convert(converter = PermissionScopeConverter.class)
    @Column(name = "scopes", columnDefinition = "json", nullable = false)
    private List<PermissionScope> scopes = new ArrayList<>();

    // 이메일 인증 여부 (미인증 사용자는 잠금(false) 처리)
    @Column(nullable = false)
    private Boolean verified;

    // 계정 활성화 여부 (소셜로그인 후 소셜회원가입 미완료 및 휴면/정지계정 false 처리)
    @Column
    private Boolean active;

    // OAuth2 처리용 메소드
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    // OAuth2 처리용 메소드
    @Override
    public String getName() {
        return this.name;
    }

    /* Default Override Methods */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.scopes;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @JsonGetter("username")
    public String getProfileUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.verified;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.active;
    }
    
    // UUID 생성 세부로직 설정
    @PrePersist
    public void createId() {
        this.id = UuidCreator.getTimeOrdered();
    }
}
