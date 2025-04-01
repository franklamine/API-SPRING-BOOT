package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "etudiants")
public class EtudiantControler {
    //    @Autowired
    private EtudiantService etudiantService;

    public EtudiantControler(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

//    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping
    public ResponseEntity<List<Etudiant>> getEtudiant() {
        return etudiantService.getEtudiant();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.createEtudiant(etudiant);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.updateEtudiant(etudiant, id);
    }
    @PatchMapping(path = "{id}")
    public ResponseEntity<Etudiant> patialUpdateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.patialUpdateEtudiant(etudiant, id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Etudiant> DeleteEtudiant(@PathVariable Long id) {
        return  etudiantService.deleteEtudiant(id);
    }
}
