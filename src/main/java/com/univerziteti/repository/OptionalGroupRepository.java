package com.univerziteti.repository;

import com.univerziteti.model.OptionalGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionalGroupRepository extends JpaRepository<OptionalGroup, Long> {
    OptionalGroup findOneById(Long id);
}
