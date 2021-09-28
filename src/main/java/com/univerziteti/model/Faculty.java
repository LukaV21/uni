package com.univerziteti.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter@Setter@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String shortDescription;
    @Column
    private String detailedDescription;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FieldOfStudy> fieldOfStudies = new ArrayList<>();

}
