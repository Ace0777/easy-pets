package br.com.vianna.edu.EasyPets.Model.animal;


import br.com.vianna.edu.EasyPets.Model.user.EtipoUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "animais")
@Entity(name = "Animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String peso;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private EtipoSexo sexo;

    @Enumerated(EnumType.STRING)
    private EtipoAnimal tipoAnimal;

}
