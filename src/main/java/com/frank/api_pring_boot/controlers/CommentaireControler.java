package com.frank.api_pring_boot.controlers;

import com.frank.api_pring_boot.entites.Commentaire;
import com.frank.api_pring_boot.enumerateurs.TypeComentaire;
import com.frank.api_pring_boot.services.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "commentaires")
public class CommentaireControler {
     private CommentaireService commentaireService;

    public CommentaireControler(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Commentaire createComment(@RequestBody Commentaire commentaire) {
        return commentaireService.createComment(commentaire);
    }

    @GetMapping
    public List<Commentaire> getComment(@RequestParam(required = false) TypeComentaire type) {
        return commentaireService.getComment(type);
    }

    @GetMapping(path = "{id}")
    public Commentaire getCommentById(@PathVariable Long id) {
        return commentaireService.getComentById(id);
    }

    @PutMapping(path = "{id}")
    public Commentaire updateComment(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        return commentaireService.updateComment(id,commentaire);
    }

    @DeleteMapping(path = "{id}")
    public void deleteComment(@PathVariable Long id){
        commentaireService.deleteComment(id);
    }
}
