package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.enums.EspecialidadesType;

public record DadosListagemMedicos(Long id,
                                   String nome,
                                   String email,
                                   String crm,
                                   EspecialidadesType especialidade) {

    public DadosListagemMedicos(MedicoEntity medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
