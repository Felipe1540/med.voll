package med.voll.api.domain.pacientes;

public record DadosListagemPacientes(Long id, String nome, String email, String telefone, String cpf) {

    public DadosListagemPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
    }
}
