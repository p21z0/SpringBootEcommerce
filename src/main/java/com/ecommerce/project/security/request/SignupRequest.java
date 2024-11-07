package com.ecommerce.project.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min=5, max=25)
    private String username;

    @NotBlank
    @Size(max=40)
    @Email
    private String email;

    @Setter
    private Set<String> role;

    @NotBlank
    @Size(min=6, max=40)
    private String password;
}
