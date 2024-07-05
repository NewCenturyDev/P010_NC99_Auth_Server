package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateCommonUserEmail {
    @Email(message = "valid.user.email.regex")
    @NotBlank(message = "valid.user.email.blank")
    @Size(max = 50, message = "valid.user.email.size")
    private String email;

    @Email(message = "valid.user.email.regex")
    @NotBlank(message = "valid.user.email.blank")
    @Size(max = 50, message = "valid.user.email.size")
    private String newEmail;
}
