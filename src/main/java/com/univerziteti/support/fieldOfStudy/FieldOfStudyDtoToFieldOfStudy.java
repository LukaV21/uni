package com.univerziteti.support.fieldOfStudy;


import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.service.FieldOfStudyService;
import com.univerziteti.web.dto.FieldOfStudyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FieldOfStudyDtoToFieldOfStudy implements Converter<FieldOfStudyDto, FieldOfStudy> {
    @Autowired
    private FieldOfStudyService fieldOfStudyService;


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
        return fieldOfStudy;
    }
}