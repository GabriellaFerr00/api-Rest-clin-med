package clin.med.apiRestclinmed.records;

import jakarta.validation.constraints.NotNull;

public record AtualizarCadastroPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
