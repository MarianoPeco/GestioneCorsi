package com.idea.gestioneCorsi.repository;

import com.idea.gestioneCorsi.entity.Corsi;
import com.idea.gestioneCorsi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsiRepository extends JpaRepository<Corsi, Integer> {
    long countByTeacher(Utente teacher);

}