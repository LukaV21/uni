package com.univerziteti.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter@NoArgsConstructor
public class FieldOfStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate accreditationDate;
    @Column
    private String shortDescription;
    @Column
    private String detailedDescription;
    @Column
    private String title;
    @ElementCollection
    private List<String> jobs;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToMany(mappedBy = "fieldOfStudies",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

}
