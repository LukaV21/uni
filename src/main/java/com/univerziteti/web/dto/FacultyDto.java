package com.univerziteti.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter@Setter@NoArgsConstructor
public class FacultyDto {
    @Positive
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private String shortDescription;
    @NotBlank
    private String detailedDescription;

}
