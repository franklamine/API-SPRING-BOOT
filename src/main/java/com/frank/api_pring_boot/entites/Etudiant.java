package com.frank.api_pring_boot.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;
    private int age;

    //    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "etudiant", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JsonIgnore // Ignore l'attribut commentaires
    @JsonManagedReference  // Sérialise les commentaires associés à l'Etudiant
    List<Commentaire> commentaireList;

}
