package br.com.vianna.edu.EasyPets.Service;

import br.com.vianna.edu.EasyPets.Model.user.User;
import br.com.vianna.edu.EasyPets.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);

        if (user != null) {
            return new UserSecurityDetails(user);
        } else {
            throw new UsernameNotFoundException("usuário não encontrado");
        }

    }
}
