package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorConsultaCancelada implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsulta dados) {
        var consultaCancelada = repository.verificandoConsultaCancelada(dados.idConsulta(), dados.motivo());
        var motivoCancela = repository.getReferenceById(dados.idConsulta());

        if ( motivoCancela.getMotivoCancelamento() != null && consultaCancelada.equals(motivoCancela.getMotivoCancelamento())) {
            throw new ValidacaoException("A consulta já foi cancelada pelo seguinte motivo: " + motivoCancela.getMotivoCancelamento());
        }
    }
}
