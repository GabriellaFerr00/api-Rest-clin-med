package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.enums.EspecialidadesType;

public record CadastrarMedico (String nome,
                               String email,
                               String crm,
                               EspecialidadesType especialidadesType,
                               DadosEndereco endereco) {
}
