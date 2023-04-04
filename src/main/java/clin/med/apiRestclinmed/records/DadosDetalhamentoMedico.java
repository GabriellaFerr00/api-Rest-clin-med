package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.entities.Endereco;
import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.enums.EspecialidadesType;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhamentoMedico(
        Long id,

        String nome,


        String email,


        String telefone,

        @Pattern(regexp = "\\d{4,6}")
        String crm,

        EspecialidadesType especialidadesType,


        Endereco endereco) {

    public DadosDetalhamentoMedico(MedicoEntity medico){
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }
}
