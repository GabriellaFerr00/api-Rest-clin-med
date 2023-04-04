package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.entities.PacienteEntity;
import clin.med.apiRestclinmed.enums.EspecialidadesType;

public record DadosListagemPacientes(String nome,
                                     String email,
                                     String cpf) {

    public DadosListagemPacientes(PacienteEntity paciente) {
        this(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
