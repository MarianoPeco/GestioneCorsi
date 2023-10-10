package com.idea.gestioneCorsi.service;

import com.idea.gestioneCorsi.entity.Utente;
import com.idea.gestioneCorsi.repository.UtenteRepository;
import com.idea.gestioneCorsi.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository repository;

    public List<Utente> getAllUtenti() {
        return repository.findAll();
    }

    public Optional<Utente> getUtente(Long id) {
        return repository.findById(id);
    }

    public Utente saveUtente(Utente utente) {

        return repository.save(utente);
    }

    public void deleteUtente(Long id) {
        repository.deleteById(id);
    }

    public Utente updateUser(Long userId, Utente updatedUser) {
        Utente existingUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setDatanascita(updatedUser.getDatanascita());

        return repository.save(existingUser);
    }

}