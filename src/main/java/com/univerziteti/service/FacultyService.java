package com.univerziteti.service;

import com.univerziteti.model.Faculty;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FacultyService {
    Optional<Faculty> findOne(Long id);

    Page<Faculty> findByName(String name, int pageNo);

    Page<Faculty> findAll(int pageNo);

    Faculty save(Faculty f);

    Faculty update(Faculty f);

    Faculty delete(Long id);
}
