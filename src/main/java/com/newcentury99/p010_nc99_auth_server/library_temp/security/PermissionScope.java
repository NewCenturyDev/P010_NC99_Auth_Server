package com.newcentury99.p010_nc99_auth_server.library_temp.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@NoArgsConstructor
public class PermissionScope implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scope_id")
    private Long id;

    // TODO: 멀티테넌시 테이블 분리하기 (area 테이블)
    @Column
    private String serviceArea;

    // TODO: 마이크로서비스 테이블 분리하기 (clientGroup 테이블 - 멀티테넌시 정보 포함)
    @Column
    private String serviceGroup;

    // TODO: 마이크로서비스 테이블 분리하기 (client 테이블 - 멀티테넌시 정보 포함)
    @Column
    private String serviceName;

    @Column
    private String permission;


    /* 생성자 */
    public PermissionScope(String serviceArea, String serviceGroup, String serviceName, String permission) {
        this.serviceArea = serviceArea;
        this.serviceGroup = serviceGroup;
        this.serviceName = serviceName;
        this.permission = permission;
    }

    public PermissionScope(String jsonString) {
        String[] scopeParams = jsonString.split("_", 4);
        this.serviceArea = scopeParams[0];
        this.serviceGroup = scopeParams[1];
        this.serviceName = scopeParams[2];
        this.permission = scopeParams[3];
    }

    @Override
    public String getAuthority() {
        return this.serviceArea + "_" + this.serviceGroup + "_" + this.serviceName + "_" + this.permission;
    }
}
