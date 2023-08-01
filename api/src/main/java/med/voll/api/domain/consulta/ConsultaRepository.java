package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consultas, Long> {
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);


    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);


    @Query("""
            select c.motivoCancelamento
            from   Consultas c
            where  c.motivoCancelamento = :motivo
            and    c.id  = :idConsulta
            """)
    String verificandoConsultaCancelada(Long idConsulta, MotivoCancelamento motivo);
}
