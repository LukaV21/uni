package com.univerziteti.service.impl;

import com.univerziteti.model.OptionalGroup;
import com.univerziteti.repository.OptionalGroupRepository;
import com.univerziteti.service.OptionalGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaOptionalGroupService implements OptionalGroupService {
    @Autowired
    private OptionalGroupRepository optionalGroupRepository;

    @Override
    public OptionalGroup findOneById(Long id) {
        return optionalGroupRepository.findOneById(id);
    }
}
