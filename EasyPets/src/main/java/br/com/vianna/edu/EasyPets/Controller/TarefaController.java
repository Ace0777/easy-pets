package br.com.vianna.edu.EasyPets.Controller;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import br.com.vianna.edu.EasyPets.Model.animal.AnimalRepository;
import br.com.vianna.edu.EasyPets.Model.tarefa.Tarefa;
import br.com.vianna.edu.EasyPets.Model.tarefa.TarefaRepository;
import br.com.vianna.edu.EasyPets.Model.user.User;
import br.com.vianna.edu.EasyPets.Model.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cadastroTarefa")
    public String tarefa() {
        return "listaAnimaisCuidado";
    }

    @PostMapping("/tarefa/cadastrar")
    public String cadastrarTarefa
            (@RequestParam("animalId") Long animalId,
            @RequestParam("descricao") String descricao,
            @RequestParam("dataRealizacao") LocalDate dataRealizacao,
            @RequestParam("dataLimite") LocalDate dataLimite,
            HttpSession session)
    {
        User cuidador = (User) session.getAttribute("usuarioLogado");
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setAnimal(animal);
        tarefa.setCuidador(cuidador);
        tarefa.setDataRealizacaoTarefa(dataRealizacao);
        tarefa.setDataLimiteTarefa(dataLimite);
        tarefa.setRealizada(false);

        tarefaRepository.save(tarefa);

        return "redirect:/home";
    }

    @GetMapping("/tarefa/listaTarefas")
    public String mostrarTarefas(Model model, HttpSession session) {
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        Long userId = usuarioLogado.getId();

        List<Tarefa> tarefas = tarefaRepository.findByCuidadorId(userId);

        model.addAttribute("tarefas", tarefas);

        return "listaTarefas";
    }



}
