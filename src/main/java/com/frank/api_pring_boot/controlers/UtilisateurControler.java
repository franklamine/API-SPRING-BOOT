package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Utilisateur;
import com.frank.api_pring_boot.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping(path = "utilisateurs") // Route de base pour tous les endpoints de cette classe
public class UtilisateurControler {

    private UtilisateurService utilisateurService;

    // Injection via constructeur (bonne pratique pour faciliter les tests)
    public UtilisateurControler(UtilisateurService etudiantService) {
        this.utilisateurService = etudiantService;
    }

    @ResponseStatus(value = HttpStatus.CREATED) // Indique que l'opération retourne un code 201
    @PostMapping(path = "inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.inscription(utilisateur);
    }

    @ResponseStatus(value = HttpStatus.CREATED) // Indique que l'opération retourne un code 201
    @PostMapping(path = "connexion")
    public ResponseEntity<?> connexion(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.connexion(utilisateur);
    }

    // Récupérer la liste de tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getUtilisateur() {
        return utilisateurService.getUtilisateur();
    }

    // Récupérer un étudiant par son ID (ex: /utilisateurs/5)
    @GetMapping(path = "{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // Mettre à jour complètement un utilisateur (remplace tous les champs)
    @PutMapping(path = "{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable Long id) {
        return utilisateurService.updateUtilisateur(utilisateur, id);
    }

    // Mise à jour partielle d’un utilisateur (modifie un ou plusieurs champs)
    @PatchMapping(path = "{id}")
    public ResponseEntity<Utilisateur> patialUpdateUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable Long id) {
        return utilisateurService.patialUpdateUtilisateur(utilisateur, id);
    }

    // Supprimer un utilisateur
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Utilisateur> DeleteUtilisateur(@PathVariable Long id) {
        return utilisateurService.deleteUtilisateur(id);
    }
}
