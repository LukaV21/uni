package com.univerziteti.repository;

import com.univerziteti.model.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Long> {
    FieldOfStudy findOneById(Long id);
}
