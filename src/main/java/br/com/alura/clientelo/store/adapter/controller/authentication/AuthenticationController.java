package br.com.alura.clientelo.store.adapter.controller.authentication;

import br.com.alura.clientelo.store.adapter.controller.authentication.dto.AuthenticationRequest;
import br.com.alura.clientelo.store.adapter.controller.authentication.dto.AuthenticationResponse;
import br.com.alura.clientelo.store.core.entity.user.User;
import br.com.alura.clientelo.store.core.usecase.authentication.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticate = manager.authenticate(authToken);
        String JWTtoken = tokenService.generateToken((User) authenticate.getPrincipal());

        return new ResponseEntity<>(new AuthenticationResponse(JWTtoken), HttpStatus.OK);
    }

}
