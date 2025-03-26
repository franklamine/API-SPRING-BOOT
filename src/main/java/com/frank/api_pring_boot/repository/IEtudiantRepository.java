package com.frank.api_pring_boot.repository;

import com.frank.api_pring_boot.entites.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByEmail(String email);
}
