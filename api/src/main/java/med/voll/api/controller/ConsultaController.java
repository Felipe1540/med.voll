package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados) {
        System.out.println(dados);
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }


      @GetMapping
      public ResponseEntity<Page<DadosDetalhamentoConsulta>> listarConsulta(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
           var page = repository.findAll(paginacao).map(DadosDetalhamentoConsulta::new);

           return ResponseEntity.ok(page);
      }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
