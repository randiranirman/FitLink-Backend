package org.fitlink.fitlinkbackend.Dto;

public record AuthResponse(String   accessToken    , String  email  , String name , String role  ) {
}
