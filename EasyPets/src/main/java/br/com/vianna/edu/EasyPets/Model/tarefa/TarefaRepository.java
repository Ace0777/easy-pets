package br.com.vianna.edu.EasyPets.Model.tarefa;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t WHERE t.cuidador.id = :userId")
    List<Tarefa> findByCuidadorId(Long userId);

}
