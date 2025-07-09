package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.configuration.JwtUtils;
import com.frank.api_pring_boot.entites.Utilisateur;
import com.frank.api_pring_boot.exceptions.UtilisateurNotFoundException;
import com.frank.api_pring_boot.repository.IUtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final IUtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    // Inscrire un nouvel utilisateur
    public ResponseEntity<String> inscription(Utilisateur utilisateur) {
        Utilisateur utilisateurRechercher = utilisateurRepository.findByEmail(utilisateur.getEmail());

        // Vérifie si l'email existe déjà
        if (utilisateurRechercher == null) {
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
            utilisateur.setMotDePasseConfirmation(passwordEncoder.encode(utilisateur.getMotDePasseConfirmation()));
            utilisateurRepository.save(utilisateur);
            // Retourner unmessage de succes avec le statut de la requette
            return new ResponseEntity<>("Utilisateur enregistré avec succès", HttpStatus.CREATED);
        } else {
            throw new UtilisateurNotFoundException("Un utilisateur existe déjà avec cette adresse email. Veuillez utiliser une autre adresse email.");
        }
    }

    // Connecter un utilisateur
    public ResponseEntity<?> connexion(Utilisateur utilisateur) {
        try {
            // Vérifie les identifiants avec le gestionnaire d'authentification
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getEmail(), utilisateur.getMotDePasse()));
            if(authentication.isAuthenticated()) {
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(utilisateur.getEmail()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
//            throw new UtilisateurNotFoundException("L'email ou mot de passe incorrect");
        }
    }

    // Récupérer tous les étudiants
    public ResponseEntity<List<Utilisateur>> getUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        // Si la liste est vide, lancer une exception personnalisée
        if (utilisateurs.isEmpty()) {
            throw new UtilisateurNotFoundException("La liste des utilisateurs est vide");
        } else {
            return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
        }
    }

    // Récupérer un utilisateur par son ID
    public ResponseEntity<Utilisateur> getUtilisateurById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        // Vérifie si l'utilisateur existe
        if (utilisateur.isPresent()) {
            return new ResponseEntity<>(utilisateur.get(), HttpStatus.OK);
        } else {
            throw new UtilisateurNotFoundException("Utilisateur non trouvé");
        }
    }


    // Mettre à jour complètement un utilisateur
    public ResponseEntity<Utilisateur> updateUtilisateur(Utilisateur utilisateur, Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        // Vérifie si l'utilisateur existe
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateurAModiffier = optionalUtilisateur.get();

            // Met à jour tous les champs
            utilisateurAModiffier.setNom(utilisateur.getNom());
            utilisateurAModiffier.setPrenom(utilisateur.getPrenom());
            utilisateurAModiffier.setEmail(utilisateur.getEmail());
            utilisateurAModiffier.setMotDePasse(utilisateur.getMotDePasse());
            utilisateurAModiffier.setMotDePasseConfirmation(utilisateur.getMotDePasseConfirmation());

            utilisateurRepository.save(utilisateurAModiffier);
            return new ResponseEntity<>(utilisateurAModiffier, HttpStatus.CREATED);
        } else {
            throw new UtilisateurNotFoundException("L'utilisateur que vous voulez modifier n'existe pas");
        }
    }

    // Mise à jour partielle d'un étudiant (PATCH)
    public ResponseEntity<Utilisateur> patialUpdateUtilisateur(Utilisateur utilisateur, Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        // Vérifie si l'utilisateur existe
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateurAModiffier = optionalUtilisateur.get();

            // Met à jour uniquement les champs non nuls ou non vides
            if (utilisateur.getEmail() != null) {
                utilisateurAModiffier.setEmail(utilisateur.getEmail());
            } else if (utilisateur.getNom() != null) {
                utilisateurAModiffier.setNom(utilisateur.getNom());
            } else if (utilisateur.getPrenom() != null) {
                utilisateurAModiffier.setPrenom(utilisateur.getPrenom());
            }

            utilisateurRepository.save(utilisateurAModiffier);
            return new ResponseEntity<>(utilisateurAModiffier, HttpStatus.CREATED);
        } else {
            throw new UtilisateurNotFoundException("L'utilisateur que vous voulez modifier n'existe pas");
        }
    }

    // Supprimer un utilisateur
    public ResponseEntity<Utilisateur> deleteUtilisateur(Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        // Vérifie si l'utilisateur existe
        if (optionalUtilisateur.isPresent()) {
            // Suppression via l’ID (plus optimal)
            utilisateurRepository.deleteById(id);
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        } else {
            throw new UtilisateurNotFoundException("L'utilisateur que vous voulez supprimer n'existe pas");
        }
    }

    // Lire un étudiant par email ou le créer s'il n'existe pas
    public Utilisateur readOrCreate(Utilisateur utilisateurACreer) {
        Utilisateur utilisateurDansLaBD = utilisateurRepository.findByEmail(utilisateurACreer.getEmail());

        // Crée un nouvel étudiant si l'email n'existe pas
        if (utilisateurDansLaBD == null) {
            utilisateurDansLaBD = utilisateurRepository.save(utilisateurACreer);
        }

        return utilisateurDansLaBD;
    }
}
