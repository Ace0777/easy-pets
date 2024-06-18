package br.com.vianna.edu.EasyPets.infra;

import br.com.vianna.edu.EasyPets.Service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private AuthorizationService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers
                ("/style.css","/css/**", "/images/**", "/js/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests( (authorize) -> authorize
                .requestMatchers("/", "/login", "/logout", "/negado", "/usuario/meusDados").permitAll()
                .requestMatchers(
                        /*"/usuario/cadastroUsuario",
                        "/usuario/listaUsuarios",
                        "/tarefa/cadastroTarefa",
                        "/tarefa/cadastroTarefa/*",
                        "/animal/listaAnimais",
                        "/animal/cadastroAnimal",
                        "/animal/listaAnimaisTarefa",
                        "/animal/remover",
                        "/animal/remover/*",
                        "/animal/atualizar",
                        "/animal/atualizar/*","/animal/*",*/
                        "/animal/**",
                        "/usuario/**",
                        "/tarefa/**"
                       ).hasRole("ADMINISTRADOR")

                        .requestMatchers("/tarefa/cadastroTarefa",
                                "/tarefa/cadastroTarefa/*",
                                "/animal/listaAnimais",
                                "/animal/cadastroAnimal",
                                "/animal/listaAnimaisTarefa"
                        ).hasRole("CUIDADOR")

                .anyRequest().authenticated()
        ) .formLogin(form -> form
                .loginPage("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
        ).logout( (logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .permitAll()
                )
                .exceptionHandling( (ex) -> ex
                .accessDeniedPage("/negado")
        ).csrf(csrf->csrf.disable());
        return http.build();
    }


}
