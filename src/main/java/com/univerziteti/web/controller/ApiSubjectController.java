package com.univerziteti.web.controller;


import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.model.Subject;
import com.univerziteti.service.OptionalGroupService;
import com.univerziteti.service.SubjectService;
import com.univerziteti.support.subject.SubjectDtoToSubject;
import com.univerziteti.support.subject.SubjectToSubjectDto;
import com.univerziteti.web.dto.FieldOfStudyDto;
import com.univerziteti.web.dto.subject.SubjectDto;
import com.univerziteti.web.dto.subject.SubjectOptionalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private OptionalGroupService optionalGroupService;
    @Autowired
    private SubjectToSubjectDto toSubjectDto;
    @Autowired
    private SubjectDtoToSubject toSubject;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<SubjectDto>> get(@RequestParam(defaultValue = "0") int page){
        Page<Subject> subjects = subjectService.findAll(page);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(subjects.getTotalPages()));
        return new ResponseEntity<>(toSubjectDto.convert(subjects.getContent()),headers, HttpStatus.OK);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> get(@PathVariable Long id) {
        Optional<Subject> subjectOptional = subjectService.findOne(id);

        if (subjectOptional.isPresent()) {
            return new ResponseEntity<>(toSubjectDto.convert(subjectOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDto> create(@Valid @RequestBody SubjectOptionalDto dto) {

        Subject s = toSubject.convert(dto);
        if(dto.getIsOptional()) {
            s.setOptionalGroup(optionalGroupService.findOneById(dto.getOptionalId()));
        }
        Subject saved = subjectService.save(s);

        return new ResponseEntity<>(toSubjectDto.convert(saved), HttpStatus.CREATED);
    }
}
