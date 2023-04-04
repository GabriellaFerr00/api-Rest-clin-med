package clin.med.apiRestclinmed.records;

import clin.med.apiRestclinmed.enums.EspecialidadesType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarMedico (
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        EspecialidadesType especialidadesType,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
