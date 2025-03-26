package com.frank.api_pring_boot.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.frank.api_pring_boot.enumerateurs.TypeComentaire;
import jakarta.persistence.*;

@Entity
@Table(name = "commentaires")
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texte;

    @Enumerated(EnumType.STRING)
    private TypeComentaire type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference // Ignore la référence à l'Etudiant dans le Commentaire pour éviter la boucle
//    @JoinColumn(name = "etudiant_id") //pour changer le nom de la clés etrangere  par defaut
    private Etudiant etudiant;

    public Commentaire() {
    }

    public Commentaire(String texte, TypeComentaire type, Etudiant etudiant) {
        this.texte = texte;
        this.type = type;
        this.etudiant = etudiant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public TypeComentaire getType() {
        return type;
    }

    public void setType(TypeComentaire type) {
        this.type = type;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
