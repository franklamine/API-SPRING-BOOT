package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.exceptions.EtudiantNotFoundException;
import com.frank.api_pring_boot.repository.IEtudiantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    //    @Autowired
    private IEtudiantRepository etudiantRepository;

    public EtudiantService(IEtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public ResponseEntity<List<Etudiant>> getEtudiant() {

        List<Etudiant> etudiants = etudiantRepository.findAll();
        if (etudiants.isEmpty()) {
            throw new EtudiantNotFoundException("La liste des etudiants est vide");
        }else {
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        }
    }


    public ResponseEntity<Etudiant> getEtudiantById(Long id) {

        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if (etudiant.isPresent()) {
            return new  ResponseEntity<>(etudiant.get(), HttpStatus.OK);
        }else {
            throw  new EtudiantNotFoundException("Etudiant non trouvé");
        }
    }


    public ResponseEntity<Etudiant> createEtudiant(Etudiant etudiant) {

        Etudiant etudiantRechercher = etudiantRepository.findByEmail(etudiant.getEmail());
        if (etudiantRechercher == null) {
            etudiantRepository.save(etudiant);
            return new ResponseEntity<>(etudiantRepository.findById(etudiant.getId()).get(), HttpStatus.CREATED);
        }else{
            throw new EtudiantNotFoundException("Un etudiant existe déja avec cette adresse email . Veillez utilisez une autre adresse email.");
        }
    }

    public ResponseEntity<Etudiant> updateEtudiant(Etudiant etudiant, Long id) {

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if(optionalEtudiant.isPresent()) {
            Etudiant etuAModiffier = optionalEtudiant.get();
            etuAModiffier.setNom(etudiant.getNom());
            etuAModiffier.setPrenom(etudiant.getPrenom());
            etuAModiffier.setEmail(etudiant.getEmail());
            etuAModiffier.setAge(etudiant.getAge());
            etudiantRepository.save(etuAModiffier);
            return new ResponseEntity<>(etuAModiffier, HttpStatus.CREATED);
        }else{
            throw new EtudiantNotFoundException("L'etudiant que vous voulez modifier n'existe pas");
        }

    }

    public ResponseEntity<Etudiant> patialUpdateEtudiant(Etudiant etudiant, Long id) {

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if (optionalEtudiant.isPresent()) {
            Etudiant etuAModiffier = optionalEtudiant.get();
            if (etudiant.getEmail() != null) {
                etuAModiffier.setEmail(etudiant.getEmail());
            } else if (etudiant.getNom() != null) {
                etuAModiffier.setNom(etudiant.getNom());
            } else if (etudiant.getPrenom() != null) {
                etuAModiffier.setPrenom(etudiant.getPrenom());
            } else if (etudiant.getAge() !=0) {
                etuAModiffier.setAge(etudiant.getAge());
            }
            etudiantRepository.save(etuAModiffier);
            return new ResponseEntity<>(etuAModiffier, HttpStatus.CREATED);
        }else {
            throw new EtudiantNotFoundException("l'etudiant que voulez modifier n'existe pas");
        }

    }

    public ResponseEntity<Etudiant> deleteEtudiant(Long id) {

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if (optionalEtudiant.isPresent()) {
//            etudiantRepository.delete(optionalEtudiant.get());
            etudiantRepository.deleteById(id);
            return new ResponseEntity<>(optionalEtudiant.get(), HttpStatus.OK);
        }else{
            throw new EtudiantNotFoundException("L'etudiant que vous voulez supprimer n'existe pas");
        }

    }

    public Etudiant readOrCreate(Etudiant etudiantACreer) {
        Etudiant etudiantDAnsLaBD = etudiantRepository.findByEmail(etudiantACreer.getEmail());
        if (etudiantDAnsLaBD == null) {
          etudiantDAnsLaBD =  etudiantRepository.save(etudiantACreer);
        }
        return etudiantDAnsLaBD;
    }
}
