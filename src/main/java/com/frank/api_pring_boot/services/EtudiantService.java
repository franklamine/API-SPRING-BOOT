package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.repository.IEtudiantRepository;
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

    public List<Etudiant> getEtudiant() {
        return etudiantRepository.findAll();
    }


    public Etudiant getEtudiantById(Long id) {
        Etudiant etudiantTrouver = null;
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if (etudiant.isPresent()) {
            etudiantTrouver = etudiant.get();
        }
        return etudiantTrouver;
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        Etudiant etudiantRechercher = etudiantRepository.findByEmail(etudiant.getEmail());
        if (etudiantRechercher == null) {
            etudiantRepository.save(etudiant);
        }
        return etudiantRepository.findById(etudiant.getId()).get();
    }

    public Etudiant updateEtudiant(Etudiant etudiant, Long id) {
        Etudiant etuAModiffier = etudiantRepository.findById(id).get();
        etuAModiffier.setNom(etudiant.getNom());
        etuAModiffier.setPrenom(etudiant.getPrenom());
        etuAModiffier.setEmail(etudiant.getEmail());
        etuAModiffier.setAge(etudiant.getAge());
        etudiantRepository.save(etuAModiffier);
        return etuAModiffier;
    }

    public Etudiant patialUpdateEtudiant(Etudiant etudiant, Long id) {
        Etudiant etuAModiffier = etudiantRepository.findById(id).get();
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
        return etuAModiffier;
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public Etudiant readOrCreate(Etudiant etudiantACreer) {
        Etudiant etudiantDAnsLaBD = etudiantRepository.findByEmail(etudiantACreer.getEmail());
        if (etudiantDAnsLaBD == null) {
          etudiantDAnsLaBD =  etudiantRepository.save(etudiantACreer);
        }
        return etudiantDAnsLaBD;
    }
}
