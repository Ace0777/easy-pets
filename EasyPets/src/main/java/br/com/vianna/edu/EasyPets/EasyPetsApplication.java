package br.com.vianna.edu.EasyPets;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import br.com.vianna.edu.EasyPets.Model.animal.AnimalRepository;
import br.com.vianna.edu.EasyPets.Model.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyPetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyPetsApplication.class, args);
	}

}
