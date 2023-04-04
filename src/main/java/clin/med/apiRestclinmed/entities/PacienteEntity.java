package clin.med.apiRestclinmed.entities;

import clin.med.apiRestclinmed.records.CadastrarPaciente;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "pacientes_tb")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public PacienteEntity(CadastrarPaciente cadastrarPaciente) {
        this.nome = cadastrarPaciente.nome();
        this.email = cadastrarPaciente.email();
        this.telefone = cadastrarPaciente.telefone();
        this.cpf = cadastrarPaciente.cpf();
        this.endereco = new Endereco(cadastrarPaciente.endereco());
    }

}

