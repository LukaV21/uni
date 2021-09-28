package com.univerziteti.service.impl;

import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.repository.FieldOfStudyRepository;
import com.univerziteti.service.FieldOfStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaFieldOfStudyService implements FieldOfStudyService {
    @Autowired
    private FieldOfStudyRepository fieldOfStudyRepository;

    @Override
    public Optional<FieldOfStudy> findOne(Long id) {
        return fieldOfStudyRepository.findById(id);
    }

    @Override
    public FieldOfStudy save(FieldOfStudy f) {
        return fieldOfStudyRepository.save(f);
    }
    @Override
    public FieldOfStudy findOneById(Long id) {
        return fieldOfStudyRepository.findOneById(id);
    }

    @Override
    public Page<FieldOfStudy> findAll(int page) {
        return fieldOfStudyRepository.findAll(PageRequest.of(page, 5));
    }

    @Override
    public FieldOfStudy update(FieldOfStudy f) {
        return fieldOfStudyRepository.save(f);
    }

    @Override
    public FieldOfStudy delete(Long id) {
        FieldOfStudy fos = fieldOfStudyRepository.findOneById(id);
        fieldOfStudyRepository.delete(fos);
        return null;
    }


}
