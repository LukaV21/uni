package com.univerziteti.web.dto.subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class SubjectDto {
    private Long id;
    private String name;
    private String year;
    private String silabus;
    private Integer semester;
    private Boolean isOptional;
}
