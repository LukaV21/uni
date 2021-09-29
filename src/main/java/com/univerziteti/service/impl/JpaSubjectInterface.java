package com.univerziteti.service.impl;

import com.univerziteti.model.Subject;
import com.univerziteti.repository.SubjectRepository;
import com.univerziteti.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaSubjectInterface implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Page<Subject> findAll(int page) {
        return subjectRepository.findAll(PageRequest.of(page, 3));
    }

    @Override
    public Optional<Subject> findOne(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject save(Subject s) {
        return subjectRepository.save(s);
    }

    @Override
    public List<Subject> find(List<Long> ids) {
        return subjectRepository.findByIdIn(ids);
    }

}
