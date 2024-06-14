package br.com.vianna.edu.EasyPets.Model.user;

public enum EtipoUser {
    ADMINISTRADOR ("ADMINISTRADOR"),
    CUIDADOR ("CUIDADOR"),
    VETERINARIO ("VETERINARIO");

    private String role;

    EtipoUser(String role){
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
