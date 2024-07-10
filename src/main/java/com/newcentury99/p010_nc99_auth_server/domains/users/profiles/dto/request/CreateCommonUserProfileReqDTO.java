package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request;

import com.newcentury99.p010_nc99_auth_server.commons.base.SupportedLanguage;
import com.newcentury99.p010_nc99_auth_server.commons.base.crud.dto.request.CreateGeneralReqDTO;
import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.UserGender;
import com.newcentury99.p010_nc99_auth_server.library_temp.security.PermissionScope;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateCommonUserProfileReqDTO implements CreateGeneralReqDTO {
    @Email(message = "valid.user.email.regex")
    @NotBlank(message = "valid.user.email.blank")
    @Size(max = 50, message = "valid.user.email.size")
    private String email;

    @Size(min = 8, max = 20, message = "valid.user.password.size")
    private String password;

    @NotNull(message = "valid.user.language.null")
    private SupportedLanguage language;

    @Size(min = 2, max = 20, message = "valid.user.username.size")
    private String username;

    @Size(min = 2, max = 20, message = "valid.user.name.size")
    private String name;

    @Size(min = 1, max = 5, message = "valid.user.country.size")
    private String country;

    @PastOrPresent(message = "valid.user.birth.time")
    private LocalDate birth;

    private UserGender gender;

    @Size(min = 9, max = 13, message = "valid.user.phone.size")
    private String defaultPhone;

    @Size(min = 9, max = 13, message = "valid.user.phone.size")
    private String mobilePhone;

    @Size(min = 8, max = 13, message = "valid.user.phone.size")
    private String landlinePhone;

    @NotNull(message = "valid.user.scopes.null")
    private List<PermissionScope> scopes;
}
