package com.univerziteti.service;

import com.univerziteti.model.Subject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Page<Subject> findAll(int page);

    Optional<Subject> findOne(Long id);

    Subject save(Subject s);

    List<Subject> find(List<Long> ids);
}
