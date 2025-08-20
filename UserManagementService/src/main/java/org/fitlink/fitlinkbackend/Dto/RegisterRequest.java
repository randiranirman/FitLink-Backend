package org.fitlink.fitlinkbackend.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String  name , @Email @NotBlank String email , @NotBlank String password, @NotBlank  String confirmPassword  ) {
}
