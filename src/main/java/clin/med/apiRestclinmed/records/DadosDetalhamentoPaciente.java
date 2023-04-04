package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.entities.Endereco;
import clin.med.apiRestclinmed.entities.PacienteEntity;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,

        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        String telefone,
        Endereco endereco) {

    public DadosDetalhamentoPaciente(PacienteEntity paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco()
        );
    }
}