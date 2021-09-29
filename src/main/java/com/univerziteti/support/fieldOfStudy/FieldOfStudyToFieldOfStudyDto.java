package com.univerziteti.support.fieldOfStudy;

import com.univerziteti.model.Faculty;
import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.model.Subject;
import com.univerziteti.support.subject.SubjectToSubjectDto;
import com.univerziteti.web.dto.FacultyDto;
import com.univerziteti.web.dto.FieldOfStudyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class FieldOfStudyToFieldOfStudyDto implements Converter<FieldOfStudy, FieldOfStudyDto> {
    @Autowired
    private SubjectToSubjectDto toSubjectDto;
    @Override
    public FieldOfStudyDto convert(FieldOfStudy source) {
        FieldOfStudyDto dto = new FieldOfStudyDto();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setAccreditationDate(source.getAccreditationDate());
        dto.setShortDescription(source.getShortDescription());
        dto.setDetailedDescription(source.getDetailedDescription());
        dto.setTitle(source.getTitle());
        dto.setJobs(source.getJobs());
            List<Subject> subjects = new ArrayList<>(source.getSubjects());
            dto.setSubjects(new HashSet<>(toSubjectDto.convert(subjects)));

        return dto;
    }

    public List<FieldOfStudyDto> convert(List<FieldOfStudy> sources){
        List<FieldOfStudyDto> dtos = new ArrayList<>();

        for(FieldOfStudy f : sources) {
            FieldOfStudyDto dto = convert(f);
            dtos.add(dto);
        }

        return dtos;
    }
}
