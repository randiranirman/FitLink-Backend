package org.fitlink.fitlinkbackend.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginResponse(String   accessToken   , String refreshToken , String  email  ,  String name , String role  ) {
}
