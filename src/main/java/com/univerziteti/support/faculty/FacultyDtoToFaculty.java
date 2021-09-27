package com.univerziteti.support.faculty;

import com.univerziteti.model.Faculty;
import com.univerziteti.service.FacultyService;
import com.univerziteti.web.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyDtoToFaculty implements Converter<FacultyDto, Faculty > {
    @Autowired
    private FacultyService facultyService;


    @Override
    public Faculty convert(FacultyDto source) {
        Faculty faculty = null;
        if(source.getId() != null) {
            faculty = facultyService.findOne(source.getId()).get();
        }

        if(faculty == null) {
            faculty = new Faculty();
        }

        faculty.setName(source.getName());
        faculty.setImage(source.getImage());
        faculty.setShortDescription(source.getShortDescription());
        faculty.setDetailedDescription(source.getDetailedDescription());

        return faculty;
    }
}
