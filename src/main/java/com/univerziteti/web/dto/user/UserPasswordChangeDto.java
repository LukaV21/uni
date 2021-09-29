package com.univerziteti.web.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter@Setter
public class UserPasswordChangeDto {
    private String username;

    private String oldPassword;

    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
    private String password;

    private String repeatedPassword;
}
