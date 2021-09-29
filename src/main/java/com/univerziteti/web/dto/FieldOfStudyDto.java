package com.univerziteti.web.dto;

import com.univerziteti.web.dto.subject.SubjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter@Setter@NoArgsConstructor
public class FieldOfStudyDto {
    private Long id;
    private String name;
    private LocalDate accreditationDate;
    private String shortDescription;
    private String detailedDescription;
    private String title;
    private List<String> jobs = new ArrayList<>();
    private Set<SubjectDto> subjects = new HashSet<>();
}
