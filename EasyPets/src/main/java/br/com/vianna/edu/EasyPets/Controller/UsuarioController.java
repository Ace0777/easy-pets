package br.com.vianna.edu.EasyPets.Controller;

import br.com.vianna.edu.EasyPets.Model.user.User;
import br.com.vianna.edu.EasyPets.Model.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UserRepository repository;

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Model model) {
        model.addAttribute("usuario", new User());
        return "cadastroUsuario";
    }

    @PostMapping("/cadastrar")
    public String salvarUsuario(@ModelAttribute User usuario,HttpSession session) {
        repository.save(usuario);

        User user = (User) session.getAttribute("usuarioLogado");
        return "redirect:/home" + "?tipoUser=" + user.getTipoUser().toString();
    }

    @GetMapping("/listaUsuarios")
    public String listarUsuarios(Model model) {
        List<User> usuarios = repository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listaUsuarios";
    }

    @GetMapping("/remover/{id}")
    public String removerUsuario(@PathVariable("id") final Long id ) {
        repository.deleteById(id);
        return "redirect:/listaUsuarios";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User buscarUsuario(@PathVariable("id") final Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public User atualizarUsuario(@PathVariable Long id, @RequestBody User usuario) {
        User usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setTipoUser(usuario.getTipoUser());
        return repository.save(usuarioExistente);
    }

    //Login e Logout sendo feio pelo spring Security

/*    @PostMapping("/login")
    public String validarLogin(@RequestParam("usuario") String login, @RequestParam("senha") String senha, HttpSession session, Model model) {
        User usuario = repository.findByLogin(login);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/home" + "?tipoUser=" + usuario.getTipoUser().toString();
        }
        model.addAttribute("error", "Usuário ou senha inválidos");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }*/
}
