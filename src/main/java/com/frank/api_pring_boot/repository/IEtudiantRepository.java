package com.frank.api_pring_boot.repository;

import com.frank.api_pring_boot.entites.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface du repository pour l'entité Etudiant
// Hérite de JpaRepository pour bénéficier des opérations CRUD standard
public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Méthode personnalisée pour rechercher un étudiant par email
    // Spring Data JPA génère automatiquement l'implémentation à partir du nom de la méthode
    Etudiant findByEmail(String email);
}
