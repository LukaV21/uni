package com.univerziteti.service;

import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.web.controller.ApiFacultyController;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FieldOfStudyService {
    Optional<FieldOfStudy> findOne(Long id);

    FieldOfStudy save(FieldOfStudy f);

    Page<FieldOfStudy> findAll(int page);

    FieldOfStudy update(FieldOfStudy f);

    FieldOfStudy delete(Long id);

    FieldOfStudy findOneById(Long id);
}
