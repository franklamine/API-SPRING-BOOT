package com.frank.api_pring_boot.services;

import com.frank.api_pring_boot.entites.Utilisateur;
import com.frank.api_pring_boot.repository.IUtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUtilisateurDetailsService  implements UserDetailsService {

    // Injection du repository pour interagir avec la base de données
    private final IUtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException( "Utilisateur " +email + " n'a pas été trouvé" );
        }
        return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), Collections.singletonList(new SimpleGrantedAuthority(utilisateur.getRole())));
    }


}
