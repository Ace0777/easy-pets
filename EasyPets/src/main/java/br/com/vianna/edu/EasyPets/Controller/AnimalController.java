package br.com.vianna.edu.EasyPets.Controller;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import br.com.vianna.edu.EasyPets.Model.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    @GetMapping("/cadastroAnimal")
    public String cadastroAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "cadastroAnimal";
    }

    @PostMapping("/animal/cadastrar")
    public String salvarAnimal(@ModelAttribute Animal animal) {
        repository.save(animal);
        return "redirect:/home";
    }

    @GetMapping("/listaAnimais")
    public String listarAnimais(Model model) {
        List<Animal> animais = repository.findAll();
        model.addAttribute("animais", animais);
        return "listaAnimais";
    }
}
