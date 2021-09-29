package com.univerziteti.web.dto.subject;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SubjectOptionalDto extends SubjectDto{
    private Long optionalId;
    private String optionalName;
}
