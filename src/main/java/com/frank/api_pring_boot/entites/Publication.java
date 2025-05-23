package com.frank.api_pring_boot.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.frank.api_pring_boot.enumerateurs.TypePublication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String contenu;

    @Enumerated(EnumType.STRING)
    private TypePublication type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference // Ignore la référence à l'Etudiant dans le Commentaire pour éviter la boucle
//    @JoinColumn(name = "etudiant_id") //pour changer le nom de la clés etrangere  par defaut
    private Utilisateur utilisateur;

}
