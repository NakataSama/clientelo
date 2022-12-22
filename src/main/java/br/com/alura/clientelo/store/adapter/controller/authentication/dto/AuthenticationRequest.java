package br.com.alura.clientelo.store.adapter.controller.authentication.dto;

import jakarta.validation.constraints.NotNull;

public class AuthenticationRequest {

    @NotNull
    private final String email;
    @NotNull
    private final String password;

    public AuthenticationRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
