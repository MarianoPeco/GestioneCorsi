package com.idea.gestioneCorsi.entity;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "corsi")
public class Corsi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codCourse;
    private String coursename;
    private Timestamp creationdate;
    private int courseduration;
    private int maxparticipantnumber;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Utente teacher;  // Represents the 'Insegnante'

    @ManyToMany(mappedBy = "courses")
    private Set<Utente> students = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        creationdate = new Timestamp(System.currentTimeMillis());
    }
}