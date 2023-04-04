package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.PacienteEntity;
import clin.med.apiRestclinmed.records.CadastrarPaciente;
import clin.med.apiRestclinmed.records.DadosListagemPacientes;
import clin.med.apiRestclinmed.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
public class PacienteController {
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid CadastrarPaciente cadastrarPaciente) {
        pacienteRepository.save(new PacienteEntity(cadastrarPaciente));
    }

    @GetMapping
    public Page<DadosListagemPacientes> listarPaciente(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return pacienteRepository
                .findAll(paginacao)
                .map(DadosListagemPacientes::new);
    }
}
