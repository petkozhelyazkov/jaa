package com.example.demo.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "specialty")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Само non-null полета се включват в JSON
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") // Включва полето id в JSON
    // @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "specialty")
    // @JoinTable(
    // name = "course_specialty",
    // joinColumns = @JoinColumn(name = "specialty_id"),
    // inverseJoinColumns = @JoinColumn(name = "course_id")
    // )
    private Set<Course> courses = new HashSet<>();

        @ManyToOne
        @JoinColumn(name = "faculty_id")
        @JsonBackReference
        private Faculty faculty;

        @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY)
        private Set<Student> students;

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(id, specialty.id) && Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}