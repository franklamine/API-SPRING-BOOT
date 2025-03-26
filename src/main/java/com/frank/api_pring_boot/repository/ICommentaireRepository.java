package com.frank.api_pring_boot.repository;

import com.frank.api_pring_boot.entites.Commentaire;
import com.frank.api_pring_boot.enumerateurs.TypeComentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByType(TypeComentaire type);
}
