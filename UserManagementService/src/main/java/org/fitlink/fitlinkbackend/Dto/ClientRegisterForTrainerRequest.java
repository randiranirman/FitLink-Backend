package org.fitlink.fitlinkbackend.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record ClientRegisterForTrainerRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank( message = "id is required") String clientId ,
        @NotBlank(message = "Email is required") String email,
        @NotBlank(message = "address is required") String address  ,
        @NotBlank(message = "Contact number is required")
        @Size(min = 10, max = 15, message = "Contact number must be between 10 and 15 characters") String contactNumber,
        @NotBlank @Positive(message = "Weight must be positive") Double weight,
        @NotBlank  @Positive(message = "Height must be positive") Double height,

        @NotBlank ( message = "gender is required") String gender ,
        @NotBlank( message = "age is required") @Min(value = 0, message = "Age must be a positive number") Double age

) {}
