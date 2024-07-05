package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request;

import com.newcentury99.p010_nc99_auth_server.domains.users.profiles.entity.UserGender;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateCommonUserProfileReqDTO {
    @Size(max = 50, message = "valid.user.language.size")
    private String language;

    @Size(min = 2, max = 20, message = "valid.user.username.size")
    private String username;

    @Size(min = 1, max = 5, message = "valid.user.country.size")
    private String country;

    @PastOrPresent(message = "valid.user.birth.time")
    private LocalDate birth;
    private UserGender gender;

    @Size(min = 9, max = 13, message = "valid.user.phone.size")
    @Pattern(regexp = "(^02|^\\d{3})-(\\d{3}|\\d{4})-\\d{4}", message = "valid.user.phone.phone")
    private String phone;
}
