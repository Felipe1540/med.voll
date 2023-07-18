package med.voll.api.domain.pacientes;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, boolean ativo) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.isAtivo());
    }
}
