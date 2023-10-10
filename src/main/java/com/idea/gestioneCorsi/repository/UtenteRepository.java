package com.idea.gestioneCorsi.repository;

import com.idea.gestioneCorsi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UtenteRepository extends JpaRepository<Utente, Long> {

}
