package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        // DEVOLVENDO CÓDIGO 200 COM A LISTAGEM
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);

        // DEVOLVENDO CÓDIGO 200 COM A LISTAGEM
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        // DEVOLVENDO CÓDIGO 200 COM OS DADOS DETALHADOS DO MEDICO
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        // DEVOLVENDO CÓDIGO 204 - BOA PRATICA APRA EXCLUSAO;
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        // DEVOLVENDO CÓDIGO 200 - BOA PRATICA PARA TRAZER DETALHAMENTO DE ALGUM CADASTRO NO BANCO;
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


}
