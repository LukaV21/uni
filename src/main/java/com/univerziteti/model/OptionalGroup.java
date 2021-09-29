package com.univerziteti.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter@NoArgsConstructor
public class OptionalGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "optionalGroup", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

}
