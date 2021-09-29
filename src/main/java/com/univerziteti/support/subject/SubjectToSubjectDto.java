package com.univerziteti.support.subject;

import com.univerziteti.model.Subject;
import com.univerziteti.service.SubjectService;
import com.univerziteti.web.dto.subject.SubjectDto;
import com.univerziteti.web.dto.subject.SubjectOptionalDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectToSubjectDto implements Converter<Subject, SubjectDto> {
    @Autowired
    private SubjectService subjectService;
    @Override
    public SubjectDto convert(Subject source) {
        SubjectDto dto = new SubjectDto();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setSilabus(source.getSilabus());
        dto.setSemester(source.getSemester());
        dto.setYear(source.getYear());
        dto.setIsOptional(source.isOptional());
        if(source.isOptional()) {
            SubjectOptionalDto dtoOptional = new SubjectOptionalDto();
            BeanUtils.copyProperties(dto,dtoOptional);
            dtoOptional.setOptionalId(source.getOptionalGroup().getId());
            dtoOptional.setOptionalName(source.getOptionalGroup().getName());
            return dtoOptional;
        }


        return dto;
    }
    public List<SubjectDto> convert(List<Subject> sources){
        List<SubjectDto> dtos = new ArrayList<>();

        for(Subject f : sources) {
            SubjectDto dto = convert(f);
            dtos.add(dto);
        }

        return dtos;
    }

}
