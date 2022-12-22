package br.com.alura.clientelo.store.infra.security;

import br.com.alura.clientelo.store.core.usecase.authentication.TokenService;
import br.com.alura.clientelo.store.infra.repository.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String JWTToken = getToken(request);


        if (JWTToken != null){
            String subject = tokenService.getSubject(JWTToken);
            Optional<UserDetails> user = userRepository.findByEmail(subject);

            if (user.isPresent()) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authorizatioHeader = request.getHeader("Authorization");

        if (authorizatioHeader != null)
            return authorizatioHeader.replace("Bearer ", "");

        return null;
    }
}
