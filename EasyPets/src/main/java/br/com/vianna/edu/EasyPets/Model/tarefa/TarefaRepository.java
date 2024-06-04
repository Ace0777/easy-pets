package br.com.vianna.edu.EasyPets.Model.tarefa;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
