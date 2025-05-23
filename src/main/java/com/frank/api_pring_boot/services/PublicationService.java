package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Publication;
import com.frank.api_pring_boot.entites.Utilisateur;
import com.frank.api_pring_boot.enumerateurs.TypePublication;
import com.frank.api_pring_boot.repository.IPublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    private IPublicationRepository publicationRepository;
    private UtilisateurService utilisateurService;

    public PublicationService(IPublicationRepository commentaireRepository, UtilisateurService etudiantService) {
        this.publicationRepository = commentaireRepository;
        this.utilisateurService = etudiantService;
    }

    public Publication createPublication(Publication publication) {
        Utilisateur utilisateur = utilisateurService.readOrCreate(publication.getUtilisateur());
        publication.setUtilisateur(utilisateur);
        if(publication.getTitre().contains("pas")){
            publication.setType(TypePublication.NEGATIF);
        }else {
            publication.setType(TypePublication.POSITIF);
        }
        return publicationRepository.save(publication);

    }

    public List<Publication> getPublication(TypePublication type) {
        List<Publication> publicationList ;
        if(type == null){
            publicationList = publicationRepository.findAll();
        }else{
            publicationList = publicationRepository.findByType(type);
        }

        return publicationList;
    }

    public Publication getPublicationById(Long id) {
        Publication publicationTrouver = null;
        Publication publicationCherche = publicationRepository.findById(id).get();
        if(publicationCherche.getUtilisateur() != null) {
            publicationTrouver = publicationCherche;
        }
       return publicationTrouver;
    }

    public Publication updatePublication(Long id, Publication publication) {
        Publication publicationAmodiffier = publicationRepository.findById(id).get();
        publicationAmodiffier.setUtilisateur(utilisateurService.readOrCreate(publication.getUtilisateur()));
        publicationAmodiffier.setTitre(publication.getTitre());
        return publicationRepository.save(publicationAmodiffier);
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
