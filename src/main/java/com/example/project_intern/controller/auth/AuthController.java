package com.example.project_intern.controller.auth;

import com.example.project_intern.model.Utilisateur;
import com.example.project_intern.repository.UtilisateurRepository;
import com.example.project_intern.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UtilisateurRepository utilisateurRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UtilisateurRepository utilisateurRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getMotDePasse()
                    )
            );
            Utilisateur user = utilisateurRepository.findByEmail(request.getEmail()).get();
            String token = jwtService.generateToken(user.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
    }
}
