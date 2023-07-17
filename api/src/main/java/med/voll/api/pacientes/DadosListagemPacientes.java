package med.voll.api.pacientes;

import med.voll.api.endereco.Endereco;

public record DadosListagemPacientes(Long id, String nome, String email, String telefone, String cpf) {

    public DadosListagemPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
    }
}
