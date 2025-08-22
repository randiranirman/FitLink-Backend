package org.fitlink.fitlinkbackend.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.fitlink.fitlinkbackend.Models.AppUserRole;

public record RegisterRequest(@NotBlank( message = "name is required") String  name , @Email( message = "Email should be valid ") @NotBlank( message = "Email is required") String email , @NotBlank( message = "password is requierd " ) @Size( min =6 , message = " password should at least  6 ") String password, @NotBlank( message = " confirm password is required")  String confirmPassword , AppUserRole appUserRole , @NotBlank( message = "username is required")  String username) {
}
