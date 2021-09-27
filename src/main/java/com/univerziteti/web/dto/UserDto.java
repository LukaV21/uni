package com.univerziteti.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter@Setter
public class UserDto {

    private Long id;

    @NotBlank
    private String username;

    @NotEmpty
    @Email
    private String eMail;

    @Size(min=3, max=50)
    private String name;

    @Size(min=3, max=50)
    private String surname;
}
