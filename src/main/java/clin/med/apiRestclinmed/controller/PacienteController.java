package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.PacienteEntity;
import clin.med.apiRestclinmed.records.CadastrarPaciente;
import clin.med.apiRestclinmed.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public void cadastrar(@RequestBody @Valid CadastrarPaciente cadastrarPaciente) {
        pacienteRepository.save(new PacienteEntity(cadastrarPaciente));
    }
}
