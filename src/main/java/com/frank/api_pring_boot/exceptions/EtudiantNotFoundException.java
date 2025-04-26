package com.frank.api_pring_boot.exceptions;

// Exception personnalisée pour signaler qu’un étudiant n’a pas été trouvé
// Elle hérite de RuntimeException, donc elle est "unchecked"
public class EtudiantNotFoundException extends RuntimeException {

    // Constructeur qui permet de passer un message personnalisé à l’exception
    public EtudiantNotFoundException(String message) {
        super(message);
    }
}
