package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Etudiant;
import com.frank.api_pring_boot.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @GetMapping
    public List<Etudiant> getEtudiant() {
        return etudiantService.getEtudiant();
    }

    @GetMapping(path = "{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.createEtudiant(etudiant);
    }

    @PutMapping(path = "{id}")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.updateEtudiant(etudiant, id);
    }
    @PatchMapping(path = "{id}")
    public Etudiant patialUpdateEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id) {
        return etudiantService.patialUpdateEtudiant(etudiant, id);
    }

    @DeleteMapping(path = "{id}")
    public void DeleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }
}
