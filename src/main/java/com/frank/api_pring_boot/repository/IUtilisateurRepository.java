package com.frank.api_pring_boot.repository;

import com.frank.api_pring_boot.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface du repository pour l'entité Etudiant
// Hérite de JpaRepository pour bénéficier des opérations CRUD standard
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Méthode personnalisée pour rechercher un étudiant par email
    // Spring Data JPA génère automatiquement l'implémentation à partir du nom de la méthode
    Utilisateur findByEmail(String email);
}
