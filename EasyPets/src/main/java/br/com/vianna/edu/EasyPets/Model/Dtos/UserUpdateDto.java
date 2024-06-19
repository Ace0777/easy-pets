package br.com.vianna.edu.EasyPets.Model.Dtos;

import br.com.vianna.edu.EasyPets.Model.user.EtipoUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class UserUpdateDto {
    private String cpf, email,login, nome, senha;
    private EtipoUser tipoUser;


    public UserUpdateDto() {
    }

    public UserUpdateDto(String cpf, String email, String login, String nome, String senha, EtipoUser tipoUser) {
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.tipoUser = tipoUser;
    }

    public UserUpdateDto(String cpf, String email, String login, String nome, String senha) {
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }
}
