package com.univerziteti.web.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AuthUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
