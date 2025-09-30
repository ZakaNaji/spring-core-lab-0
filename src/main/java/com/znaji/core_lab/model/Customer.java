package com.znaji.core_lab.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Sample domain object used across the exercises.
 */
public record Customer(
        @NotBlank(message = "Customer id is required") String id,
        @NotBlank(message = "Customer name is required") String name,
        @Email(message = "Invalid customer email") String email
) {
}
