package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.services.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping(path = "etudiants") // Route de base pour tous les endpoints de cette classe
public class EtudiantControler {

    // Service injecté pour accéder à la logique métier
    private EtudiantService etudiantService;

    // Injection via constructeur (bonne pratique pour faciliter les tests)
    public EtudiantControler(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // Récupérer la liste de tous les étudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> getEtudiant() {
        return etudiantService.getEtudiant();
    }

    // Récupérer un étudiant par son ID (ex: /etudiants/5)
    @GetMapping(path = "{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    // Créer un nouvel étudiant
    @ResponseStatus(value = HttpStatus.CREATED) // Indique que l'opération retourne un code 201
    @PostMapping
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.createEtudiant(etudiant);
    }

    // Mettre à jour complètement un étudiant (remplace tous les champs)
    @PutMapping(path = "{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.updateEtudiant(etudiant, id);
    }

    // Mise à jour partielle d’un étudiant (modifie un ou plusieurs champs)
    @PatchMapping(path = "{id}")
    public ResponseEntity<Etudiant> patialUpdateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.patialUpdateEtudiant(etudiant, id);
    }

    // Supprimer un étudiant
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Etudiant> DeleteEtudiant(@PathVariable Long id) {
        return etudiantService.deleteEtudiant(id);
    }
}
