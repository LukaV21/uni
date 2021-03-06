package com.univerziteti.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String year;
    @Column
    private String silabus;
    @Column
    private int semester;
    @Column
    private boolean isOptional;
    @ManyToOne
    @JoinColumn(name = "optional_group_id")
    private OptionalGroup optionalGroup;
    @ManyToMany(mappedBy = "subjects",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<FieldOfStudy> fieldOfStudies;

}
