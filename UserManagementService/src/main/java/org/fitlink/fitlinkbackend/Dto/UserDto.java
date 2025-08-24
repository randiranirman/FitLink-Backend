package org.fitlink.fitlinkbackend.Dto;

import org.fitlink.fitlinkbackend.Models.AppUserRole;

public record UserDto(String email, String  name , String id  , AppUserRole appUserRole) {
}
