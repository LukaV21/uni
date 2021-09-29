package com.univerziteti.support.subject;

import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.model.Subject;
import com.univerziteti.service.FieldOfStudyService;
import com.univerziteti.service.OptionalGroupService;
import com.univerziteti.service.SubjectService;
import com.univerziteti.web.dto.FieldOfStudyDto;
import com.univerziteti.web.dto.subject.SubjectDto;
import com.univerziteti.web.dto.subject.SubjectOptionalDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectDtoToSubject implements Converter<SubjectDto, Subject> {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private OptionalGroupService optionalGroupService;


    @Override
    public Subject convert(SubjectDto source) {
        Subject subject = null;
        if(source.getId() != null) {
            subject = subjectService.findOne(source.getId()).get();
        }

        if(subject == null) {
            subject = new Subject();
        }
        subject.setName(source.getName());
        subject.setSilabus(source.getSilabus());
        subject.setSemester(source.getSemester());
        subject.setYear(source.getYear());
        subject.setOptional(source.getIsOptional());

        return subject;
    }

}
