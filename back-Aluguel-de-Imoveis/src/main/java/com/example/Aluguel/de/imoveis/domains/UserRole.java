package com.example.Aluguel.de.imoveis.domains;

public enum UserRole {
    ADMIN("admin"),
    PROPRIETARIO("proprietario"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}