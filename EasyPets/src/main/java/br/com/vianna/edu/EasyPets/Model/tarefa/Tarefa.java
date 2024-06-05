package br.com.vianna.edu.EasyPets.Model.tarefa;
import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import br.com.vianna.edu.EasyPets.Model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "tarefas")
@Entity(name = "Tarefa")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User cuidador;

    private LocalDate dataRealizacaoTarefa;

    private LocalDate dataLimiteTarefa;

    private boolean realizada = false;


    public Tarefa() {
        this.realizada = false;
    }

    public Tarefa(Long id, String descricao, Animal animal, User cuidador, LocalDate dataRealizacaoTarefa, LocalDate dataLimiteTarefa) {
        this.id = id;
        this.descricao = descricao;
        this.animal = animal;
        this.cuidador = cuidador;
        this.dataRealizacaoTarefa = dataRealizacaoTarefa;
        this.dataLimiteTarefa = dataLimiteTarefa;
        this.realizada = false;
    }
}