package med.voll.api.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // PARA DIZER AO SPRING QUE IREMOS PERSONALIZAR AS CONFIGURACOES DE SEGURANÇA
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     *  suponha que em nossa aplicação tenhamos um perfil de acesso chamado de ADMIN,
     *  sendo que somente usuários com esse perfil possam excluir médicos e pacientes.
     *  Podemos indicar ao Spring Security tal configuração alterando o método securityFilterChain,
     *  na classe SecurityConfigurations, da seguinte maneira:
     *
     * @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and().authorizeHttpRequests()
    .requestMatchers(HttpMethod.POST, "/login").permitAll()
    .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
    .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
    .anyRequest().authenticated()
    .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
    .build();
    }
     *
     * */

    //A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realizar a sua injeção de dependência em outras classes.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realizar a sua injeção de dependência em outras classes.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
