package com.spring.seccurity.Security.controllers.publics;

import com.spring.seccurity.Security.helpers.security.TokenService;
import com.spring.seccurity.Security.models.user.*;
import com.spring.seccurity.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     Controller de autenticação. Atraves dos endpoints o usuário poderá realizar o login e sua conta e receber o Token de
 *     de validação, ou criar um novo usuário.
 * </p>
 * <p>
 *     Como os Controllers padrões, essa classe utiliza {@link RestController} para indicar ao Spring que esse é um controller
 *     e {@link RequestMapping} para definir o path padrão como "/auth"
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    /**
     * O {@link AuthenticationManager} é utilizado pelo SpringSecurity para fazer as validações e autenticar o usuário.
     * Ele utiliza internamente a service {@link com.spring.seccurity.Security.services.AuthorizationService} para localizar
     * o usuário cadastrado no sistema.
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        // Utilizado para armazenar as credenciais do usuário que esta tentando fazer login
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        //Lança uma exceção caso a autenticação falhe
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        if (repository.findByLogin(data.login()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());

        repository.save(user);

        return ResponseEntity.ok().build();
    }
}
