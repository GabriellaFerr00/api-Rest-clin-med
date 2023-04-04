package clin.med.apiRestclinmed.entities;

import clin.med.apiRestclinmed.enums.EspecialidadesType;
import clin.med.apiRestclinmed.records.CadastrarMedico;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicos_tb")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private EspecialidadesType especialidade;
    @Embedded
    private Endereco endereco;

    public MedicoEntity(CadastrarMedico cadastrarMedico) {
        this.nome = cadastrarMedico.nome();
        this.email = cadastrarMedico.email();
        this.telefone = cadastrarMedico.telefone();
        this.crm = cadastrarMedico.crm();
        this.especialidade= cadastrarMedico.especialidadesType();
        this.endereco = new Endereco(cadastrarMedico.endereco());
    }
}
