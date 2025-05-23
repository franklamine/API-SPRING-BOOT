package com.frank.api_pring_boot.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String role;

    @Column(unique = true)
    private String email;
    private String motDePasse;
    private String motDePasseConfirmation;

    //    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JsonIgnore // Ignore l'attribut commentaires
    @JsonManagedReference  // Sérialise les commentaires associés à l'Etudiant
    List<Publication> publicationList;

}
