package com.idea.gestioneCorsi.repository;

import com.idea.gestioneCorsi.entity.Corsi;
import com.idea.gestioneCorsi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsiRepository extends JpaRepository<Corsi, Integer> {
    //JpaRepository provides a findAll method that accepts a Sort object which can be used to specify ordering.
    long countByTeacher(Utente teacher);

}