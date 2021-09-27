package com.univerziteti.service.impl;

import com.univerziteti.model.Faculty;
import com.univerziteti.repository.FacultyRepository;
import com.univerziteti.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaFacultyService implements FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Override
    public Optional<Faculty> findOne(Long id) {
        return facultyRepository.findById(id);
    }
    @Override
    public Page<Faculty> findByName(String name, int pageNo) {
        if(name==null) {
            name="";
        }
        return facultyRepository.findAllByNameIgnoreCaseContains(name, PageRequest.of(pageNo, 5));
    }
    @Override
    public Page<Faculty> findAll(int pageNo) {
        return facultyRepository.findAll(PageRequest.of(pageNo, 5));
    }

    @Override
    public Faculty save(Faculty f) {
        return facultyRepository.save(f);
    }

    @Override
    public Faculty update(Faculty f) {
        return facultyRepository.save(f);
    }

    @Override
    public Faculty delete(Long id) {
        Faculty faculty = facultyRepository.findOneById(id);
        facultyRepository.delete(faculty);
        return faculty;
    }
}
