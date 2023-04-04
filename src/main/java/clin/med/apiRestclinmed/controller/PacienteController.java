package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.PacienteEntity;
import clin.med.apiRestclinmed.records.AtualizarCadastroPaciente;
import clin.med.apiRestclinmed.records.CadastrarPaciente;
import clin.med.apiRestclinmed.records.DadosDetalhamentoPaciente;
import clin.med.apiRestclinmed.records.DadosListagemPacientes;
import clin.med.apiRestclinmed.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/pacientes")
@AllArgsConstructor
public class PacienteController {
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid CadastrarPaciente cadastrarPaciente, UriComponentsBuilder uriComponentsBuilder) {
        PacienteEntity entity = new PacienteEntity(cadastrarPaciente);
        pacienteRepository.save(entity);

        URI uri = uriComponentsBuilder.path("/api/pacientes/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(entity));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listarPaciente(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemPacientes> page = pacienteRepository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemPacientes::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable Long id){
        PacienteEntity paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity atualizarPaciente(@RequestBody @Valid AtualizarCadastroPaciente atualizar){
        PacienteEntity pacienteEntity = pacienteRepository.getReferenceById(atualizar.id());
        pacienteEntity.atualizarInformacoesPaciente(atualizar);

        return ResponseEntity.ok().body(new DadosDetalhamentoPaciente(pacienteEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        PacienteEntity paciente = pacienteRepository.getReferenceById(id);
        paciente.exclusaoLogicaPaciente();

        return ResponseEntity.noContent().build();
    }
}
