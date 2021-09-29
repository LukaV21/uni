package com.univerziteti.support.fieldOfStudy;


import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.model.Subject;
import com.univerziteti.service.FieldOfStudyService;
import com.univerziteti.service.SubjectService;
import com.univerziteti.web.dto.FieldOfStudyDto;
import com.univerziteti.web.dto.subject.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldOfStudyDtoToFieldOfStudy implements Converter<FieldOfStudyDto, FieldOfStudy> {
    @Autowired
    private FieldOfStudyService fieldOfStudyService;
    @Autowired
    private SubjectService subjectService;


    @Override
    public FieldOfStudy convert(FieldOfStudyDto source) {
        FieldOfStudy fieldOfStudy = null;
        if(source.getId() != null) {
            fieldOfStudy = fieldOfStudyService.findOne(source.getId()).get();
        }

        if(fieldOfStudy == null) {
            fieldOfStudy = new FieldOfStudy();
        }

        fieldOfStudy.setName(source.getName());
        fieldOfStudy.setAccreditationDate(source.getAccreditationDate());
        fieldOfStudy.setShortDescription(source.getShortDescription());
        fieldOfStudy.setDetailedDescription(source.getDetailedDescription());
        fieldOfStudy.setTitle(source.getTitle());
        fieldOfStudy.setJobs(source.getJobs());
        if(source.getSubjects() != null) {
            List<Long> idSubjects = source.getSubjects().stream().map(SubjectDto::getId).collect(Collectors.toList());
            List<Subject> subjects = subjectService.find(idSubjects);
            fieldOfStudy.setSubjects(new HashSet<>(subjects));
        }
        return fieldOfStudy;
    }
}