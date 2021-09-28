package com.univerziteti.web.controller;

import com.univerziteti.model.Faculty;
import com.univerziteti.model.FieldOfStudy;
import com.univerziteti.service.FieldOfStudyService;
import com.univerziteti.support.fieldOfStudy.FieldOfStudyDtoToFieldOfStudy;
import com.univerziteti.support.fieldOfStudy.FieldOfStudyToFieldOfStudyDto;
import com.univerziteti.web.dto.FacultyDto;
import com.univerziteti.web.dto.FieldOfStudyDto;
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
@RequestMapping(value = "/api/fields", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiFieldOfStudyController {
    @Autowired
    private FieldOfStudyService fieldOfStudyService;
    @Autowired
    private FieldOfStudyToFieldOfStudyDto toFieldOfStudyDto;
    @Autowired
    private FieldOfStudyDtoToFieldOfStudy toFieldOfStudy;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<FieldOfStudyDto>> get(@RequestParam(defaultValue = "0") int page){
        Page<FieldOfStudy> fos = fieldOfStudyService.findAll(page);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(fos.getTotalPages()));
        return new ResponseEntity<>(toFieldOfStudyDto.convert(fos.getContent()),headers, HttpStatus.OK);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<FieldOfStudyDto> get(@PathVariable Long id) {
        Optional<FieldOfStudy> fieldOfStudyOptional = fieldOfStudyService.findOne(id);

        if (fieldOfStudyOptional.isPresent()) {
            return new ResponseEntity<>(toFieldOfStudyDto.convert(fieldOfStudyOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldOfStudyDto> create(@Valid @RequestBody FieldOfStudyDto dto) {
        FieldOfStudy f = toFieldOfStudy.convert(dto);
        FieldOfStudy saved = fieldOfStudyService.save(f);

        return new ResponseEntity<>(toFieldOfStudyDto.convert(saved), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldOfStudyDto> update(@PathVariable Long id, @Valid @RequestBody FieldOfStudyDto dto) {

        if (!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        FieldOfStudy f = toFieldOfStudy.convert(dto);
        FieldOfStudy updated = fieldOfStudyService.update(f);

        return new ResponseEntity<>(toFieldOfStudyDto.convert(updated), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        FieldOfStudy deleted = fieldOfStudyService.delete(id);

        if (deleted != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
