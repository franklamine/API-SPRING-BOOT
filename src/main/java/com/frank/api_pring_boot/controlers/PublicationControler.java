package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Publication;
import com.frank.api_pring_boot.enumerateurs.TypePublication;
import com.frank.api_pring_boot.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "publications")
public class PublicationControler {
     private PublicationService publicationService;

    public PublicationControler(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationService.createPublication(publication);
    }

    @GetMapping
    public List<Publication> getPublication(@RequestParam(required = false) TypePublication type) {
        return publicationService.getPublication(type);
    }

    @GetMapping(path = "{id}")
    public Publication getPublicationById(@PathVariable Long id) {
        return publicationService.getPublicationById(id);
    }

    @PutMapping(path = "{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        return publicationService.updatePublication(id,publication);
    }

    @DeleteMapping(path = "{id}")
    public void deletePublication(@PathVariable Long id){
        publicationService.deletePublication(id);
    }
}
