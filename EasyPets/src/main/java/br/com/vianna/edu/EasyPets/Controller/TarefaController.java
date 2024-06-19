package br.com.vianna.edu.EasyPets.Controller;

import br.com.vianna.edu.EasyPets.Model.animal.Animal;
import br.com.vianna.edu.EasyPets.Model.animal.AnimalRepository;
import br.com.vianna.edu.EasyPets.Model.tarefa.Tarefa;
import br.com.vianna.edu.EasyPets.Model.tarefa.TarefaRepository;
import br.com.vianna.edu.EasyPets.Model.user.User;
import br.com.vianna.edu.EasyPets.Model.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/cadastroTarefa")
    public String tarefa(Model model) {
        List<Animal> animais = animalRepository.findAll();
        model.addAttribute("animais", animais);
        return "listaAnimaisTarefa";
    }

    @PostMapping("/cadastrar")
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

        return "redirect:/home" + "?tipoUser=" + cuidador.getTipoUser().toString();
    }

    @GetMapping("/listaTarefas")
    public String mostrarTarefas(Model model, HttpSession session) {
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        Long userId = usuarioLogado.getId();

        List<Tarefa> tarefas = tarefaRepository.findByCuidadorId(userId);

        model.addAttribute("tarefas", tarefas);

        return "listaTarefas";
    }

    @GetMapping("/listaTarefasRealizada/{realizada}")
    public String mostrarTarefas(Model model, HttpSession session, @PathVariable boolean realizada) {
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        Long userId = usuarioLogado.getId();

        List<Tarefa> tarefas = tarefaRepository.findByRealizada(realizada, userId);

        model.addAttribute("tarefas", tarefas);

        return "listaTarefas";
    }

    @PutMapping("/realizar/{id}")
    public ResponseEntity<Void> realizarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);
        if (tarefaOpt.isPresent()) {
            Tarefa tarefa = tarefaOpt.get();
            tarefa.setRealizada(true);
            tarefaRepository.save(tarefa);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
