package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//No repository passamos a entidade e o tipo da chave primaria dela, nesse caso Usuario e long respectivamente.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
