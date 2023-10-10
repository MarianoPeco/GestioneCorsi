package com.idea.gestioneCorsi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String name;
    private String surname;
    private int age;
    private String datanascita;
    private String cf;
    private UserType type;  // Enum type for "Insegnante" or "Alunno"

    public enum UserType {
        ALUNNO,       // Ordinal value = 0
        INSEGNANTE;   // Ordinal value = 1
    }

    protected Utente() {
    }

    @ManyToMany
    @JoinTable(
            name = "utenti_corsi",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "codCourse")
    )
    private Set<Corsi> courses = new HashSet<>();

    public Set<Corsi> getCourses() {
        return courses;
    }
}