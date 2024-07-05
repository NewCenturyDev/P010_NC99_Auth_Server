package com.newcentury99.p010_nc99_auth_server.domains.users.profiles.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCommonUserPassword {
    @NotBlank(message = "valid.user.password.blank")
    @Size(min = 8, max = 20, message = "valid.user.password.size")
    private String password;
    @NotBlank(message = "valid.user.password.blank")
    @Size(min = 8, max = 20, message = "valid.user.password.size")
    private String newPassword;
}
