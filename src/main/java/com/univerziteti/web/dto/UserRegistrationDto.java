package com.univerziteti.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto extends UserDto{
    private String password;

    private String repeatedPassword;
}
