package br.com.vianna.edu.EasyPets.Service;

import br.com.vianna.edu.EasyPets.Model.user.EtipoUser;
import br.com.vianna.edu.EasyPets.Model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {

    private User user;

    public UserSecurityDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getTipoUser() == EtipoUser.ADMINISTRADOR) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"),
                    new SimpleGrantedAuthority("ROLE_CUIDADOR"),
                    new SimpleGrantedAuthority("ROLE_VETERINARIO"));
        } else if (this.user.getTipoUser() == EtipoUser.CUIDADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_CUIDADOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_VETERINARIO"));
        }
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(user.getSenha());
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public String getTipoUserString() {
        return user.getTipoUser().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
