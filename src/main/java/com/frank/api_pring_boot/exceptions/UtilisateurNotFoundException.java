package com.frank.api_pring_boot.exceptions;

// Exception personnalisée pour signaler qu’un étudiant n’a pas été trouvé
// Elle hérite de RuntimeException, donc elle est "unchecked"
public class UtilisateurNotFoundException extends RuntimeException {

    // Constructeur qui permet de passer un message personnalisé à l’exception
    public UtilisateurNotFoundException(String message) {
        super(message);
    }
}
