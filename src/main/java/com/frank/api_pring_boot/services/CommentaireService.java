package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Commentaire;
import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.enumerateurs.TypeComentaire;
import com.frank.api_pring_boot.repository.ICommentaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {
    private ICommentaireRepository commentaireRepository;
    private EtudiantService etudiantService;

    public CommentaireService(ICommentaireRepository commentaireRepository, EtudiantService etudiantService) {
        this.commentaireRepository = commentaireRepository;
        this.etudiantService = etudiantService;
    }

    public Commentaire createComment(Commentaire commentaire) {
        Etudiant etudiant = etudiantService.readOrCreate(commentaire.getEtudiant());
        commentaire.setEtudiant(etudiant);
        if(commentaire.getTexte().contains("pas")){
            commentaire.setType(TypeComentaire.NEGATIF);
        }else {
            commentaire.setType(TypeComentaire.POSITIF);
        }
        return commentaireRepository.save(commentaire);

    }

    public List<Commentaire> getComment(TypeComentaire type) {
        List<Commentaire> commentaireList ;
        if(type == null){
            commentaireList = commentaireRepository.findAll();
        }else{
            commentaireList = commentaireRepository.findByType(type);
        }

        return commentaireList;
    }

    public Commentaire getComentById(Long id) {
        Commentaire commentaireTrouver = null;
        Commentaire commentaireCherche = commentaireRepository.findById(id).get();
        if(commentaireCherche.getEtudiant() != null) {
            commentaireTrouver = commentaireCherche;
        }
       return commentaireTrouver;
    }

    public Commentaire updateComment(Long id, Commentaire commentaire) {
        Commentaire commentaireAModiffier = commentaireRepository.findById(id).get();
        commentaireAModiffier.setEtudiant(etudiantService.readOrCreate(commentaire.getEtudiant()));
        commentaireAModiffier.setTexte(commentaire.getTexte());
        return commentaireRepository.save(commentaireAModiffier);
    }

    public void deleteComment(Long id) {
        commentaireRepository.deleteById(id);
    }
}
