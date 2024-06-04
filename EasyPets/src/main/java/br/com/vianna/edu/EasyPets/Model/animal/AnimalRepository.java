package br.com.vianna.edu.EasyPets.Model.animal;

import br.com.vianna.edu.EasyPets.Model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
