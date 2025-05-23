package com.frank.api_pring_boot.repository;

import com.frank.api_pring_boot.entites.Publication;
import com.frank.api_pring_boot.enumerateurs.TypePublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findByType(TypePublication type);
}
