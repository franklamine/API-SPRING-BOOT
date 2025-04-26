package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.exceptions.EtudiantNotFoundException;
import com.frank.api_pring_boot.repository.IEtudiantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    // Injection du repository pour interagir avec la base de données
    private IEtudiantRepository etudiantRepository;

    // Constructeur pour l'injection de dépendance
    public EtudiantService(IEtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    // Récupérer tous les étudiants
    public ResponseEntity<List<Etudiant>> getEtudiant() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        // Si la liste est vide, lancer une exception personnalisée
        if (etudiants.isEmpty()) {
            throw new EtudiantNotFoundException("La liste des étudiants est vide");
        } else {
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        }
    }

    // Récupérer un étudiant par son ID
    public ResponseEntity<Etudiant> getEtudiantById(Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);

        // Vérifie si l'étudiant existe
        if (etudiant.isPresent()) {
            return new ResponseEntity<>(etudiant.get(), HttpStatus.OK);
        } else {
            throw new EtudiantNotFoundException("Étudiant non trouvé");
        }
    }

    // Créer un nouvel étudiant
    public ResponseEntity<Etudiant> createEtudiant(Etudiant etudiant) {
        Etudiant etudiantRechercher = etudiantRepository.findByEmail(etudiant.getEmail());

        // Vérifie si l'email existe déjà
        if (etudiantRechercher == null) {
            etudiantRepository.save(etudiant);
            // On récupère à nouveau l'étudiant pour retourner l'objet complet avec son ID généré
            return new ResponseEntity<>(etudiantRepository.findById(etudiant.getId()).get(), HttpStatus.CREATED);
        } else {
            throw new EtudiantNotFoundException("Un étudiant existe déjà avec cette adresse email. Veuillez utiliser une autre adresse email.");
        }
    }

    // Mettre à jour complètement un étudiant
    public ResponseEntity<Etudiant> updateEtudiant(Etudiant etudiant, Long id) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);

        // Vérifie si l'étudiant existe
        if (optionalEtudiant.isPresent()) {
            Etudiant etuAModiffier = optionalEtudiant.get();

            // Met à jour tous les champs
            etuAModiffier.setNom(etudiant.getNom());
            etuAModiffier.setPrenom(etudiant.getPrenom());
            etuAModiffier.setEmail(etudiant.getEmail());
            etuAModiffier.setAge(etudiant.getAge());

            etudiantRepository.save(etuAModiffier);
            return new ResponseEntity<>(etuAModiffier, HttpStatus.CREATED);
        } else {
            throw new EtudiantNotFoundException("L'étudiant que vous voulez modifier n'existe pas");
        }
    }

    // Mise à jour partielle d'un étudiant (PATCH)
    public ResponseEntity<Etudiant> patialUpdateEtudiant(Etudiant etudiant, Long id) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);

        // Vérifie si l'étudiant existe
        if (optionalEtudiant.isPresent()) {
            Etudiant etuAModiffier = optionalEtudiant.get();

            // Met à jour uniquement les champs non nuls ou non vides
            if (etudiant.getEmail() != null) {
                etuAModiffier.setEmail(etudiant.getEmail());
            } else if (etudiant.getNom() != null) {
                etuAModiffier.setNom(etudiant.getNom());
            } else if (etudiant.getPrenom() != null) {
                etuAModiffier.setPrenom(etudiant.getPrenom());
            } else if (etudiant.getAge() != 0) {
                etuAModiffier.setAge(etudiant.getAge());
            }

            etudiantRepository.save(etuAModiffier);
            return new ResponseEntity<>(etuAModiffier, HttpStatus.CREATED);
        } else {
            throw new EtudiantNotFoundException("L'étudiant que vous voulez modifier n'existe pas");
        }
    }

    // Supprimer un étudiant
    public ResponseEntity<Etudiant> deleteEtudiant(Long id) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);

        // Vérifie si l'étudiant existe
        if (optionalEtudiant.isPresent()) {
            // Suppression via l’ID (plus optimal)
            etudiantRepository.deleteById(id);
            return new ResponseEntity<>(optionalEtudiant.get(), HttpStatus.OK);
        } else {
            throw new EtudiantNotFoundException("L'étudiant que vous voulez supprimer n'existe pas");
        }
    }

    // Lire un étudiant par email ou le créer s'il n'existe pas
    public Etudiant readOrCreate(Etudiant etudiantACreer) {
        Etudiant etudiantDAnsLaBD = etudiantRepository.findByEmail(etudiantACreer.getEmail());

        // Crée un nouvel étudiant si l'email n'existe pas
        if (etudiantDAnsLaBD == null) {
            etudiantDAnsLaBD = etudiantRepository.save(etudiantACreer);
        }

        return etudiantDAnsLaBD;
    }
}
