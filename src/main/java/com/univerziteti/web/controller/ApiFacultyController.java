package com.univerziteti.web.controller;

import com.univerziteti.model.Faculty;
import com.univerziteti.service.FacultyService;
import com.univerziteti.support.faculty.FacultyDtoToFaculty;
import com.univerziteti.support.faculty.FacultyToFacultyDto;
import com.univerziteti.web.dto.FacultyDto;
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
@RequestMapping(value = "/api/faculties", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiFacultyController {
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyToFacultyDto toFacultyDto;
    @Autowired
    private FacultyDtoToFaculty toFaculty;


    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAll(
            @RequestParam(required=false, defaultValue = "") String name,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
        Page<Faculty> page;
        try{
            page = facultyService.findByName(name, pageNo);
        }catch (Exception e){
            page = facultyService.findAll(pageNo);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toFacultyDto.convert(page.getContent()),headers, HttpStatus.OK);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> get(@PathVariable Long id) {
        Optional<Faculty> facultyOptional = facultyService.findOne(id);

        if (facultyOptional.isPresent()) {
            return new ResponseEntity<>(toFacultyDto.convert(facultyOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDto> create(@Valid @RequestBody FacultyDto dto) {
        Faculty f = toFaculty.convert(dto);
        Faculty saved = facultyService.save(f);

        return new ResponseEntity<>(toFacultyDto.convert(saved), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDto> update(@PathVariable Long id, @Valid @RequestBody FacultyDto dto) {

        if (!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Faculty f = toFaculty.convert(dto);
        Faculty updated = facultyService.update(f);

        return new ResponseEntity<>(toFacultyDto.convert(updated), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Faculty deleted = facultyService.delete(id);

        if (deleted != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
