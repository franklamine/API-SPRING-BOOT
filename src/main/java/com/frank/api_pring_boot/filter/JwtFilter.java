package com.frank.api_pring_boot.filter;

import com.frank.api_pring_boot.configuration.JwtUtils;
import com.frank.api_pring_boot.services.CustomUtilisateurDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final CustomUtilisateurDetailsService customUtilisateurDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         final String authorizationHeader = request.getHeader("Authorization");
         String username = null;
         String jwtToken = null;
         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             jwtToken = authorizationHeader.substring(7);
             username = jwtUtils.extractUsername(jwtToken);
         }
         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
             UserDetails userDetails = customUtilisateurDetailsService.loadUserByUsername(username);

             if (jwtUtils.validateToken(jwtToken, userDetails)) {
                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //pour le login et cest optionnel. pour plustart
                 SecurityContextHolder.getContext().setAuthentication(authentication);
             }
         }
         filterChain.doFilter(request, response);
    }
}
