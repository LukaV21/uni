package com.univerziteti.support.faculty;

import com.univerziteti.model.Faculty;
import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.support.fieldOfStudy.FieldOfStudyToFieldOfStudyDto;
import com.univerziteti.web.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FacultyToFacultyDto implements Converter<Faculty, FacultyDto> {
    @Autowired
    private FieldOfStudyToFieldOfStudyDto toFieldOfStudyDto;
    @Override
    public FacultyDto convert(Faculty source) {
        FacultyDto dto = new FacultyDto();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setImage(source.getImage());
        dto.setShortDescription(source.getShortDescription());
        dto.setDetailedDescription(source.getDetailedDescription());

        List<FieldOfStudy> fieldOfStudyList = new ArrayList<>(source.getFieldOfStudies());
        dto.setFieldOfStudies(new ArrayList<>(toFieldOfStudyDto.convert(fieldOfStudyList)));

        return dto;
    }

    public List<FacultyDto> convert(List<Faculty> faculties){
        List<FacultyDto> dtos = new ArrayList<>();

        for(Faculty f : faculties) {
            FacultyDto dto = convert(f);
            dtos.add(dto);
        }

        return dtos;
    }
}
