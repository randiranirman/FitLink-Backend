package org.fitlink.fitlinkbackend.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(  @NotBlank(  message = "user is  required ")  String username , @NotBlank( message =  " password is required ")  String password) {

}
