package br.com.alura.clientelo.store.interfaces.controller.authentication.dto;

public class AuthenticationResponse {

    private String token;
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String JWTtoken) {
        this.token = JWTtoken;
    }
}
