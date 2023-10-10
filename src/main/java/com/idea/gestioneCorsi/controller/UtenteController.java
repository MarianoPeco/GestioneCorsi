package com.idea.gestioneCorsi.controller;

import com.idea.gestioneCorsi.entity.Utente;
import com.idea.gestioneCorsi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService service;

    @GetMapping("/all")
    public List<Utente> getAllUtenti() {
        return service.getAllUtenti();
    }

    @GetMapping("/{id}")
    public Utente getUtenteById(@PathVariable Long id) {
        return service.getUtente(id).orElse(null);
    }

    @PostMapping("/add")
    public Utente addUtente(@RequestBody Utente utente) {
        return service.saveUtente(utente);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Utente> updateUser(@PathVariable Long userId, @RequestBody Utente updatedUser) {
        Utente user = service.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUtente(@PathVariable Long id) {
        service.deleteUtente(id);
        return "Deleted user with ID: " + id;
    }
}
