package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.Endereco;
import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.records.CadastrarMedico;
import clin.med.apiRestclinmed.records.DadosListagemMedicos;
import clin.med.apiRestclinmed.repositories.MedicoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@AllArgsConstructor
public class MedicoController {

    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastrarMedico cadastrarMedico){
         medicoRepository.save(new MedicoEntity(cadastrarMedico));
    }

    @GetMapping
    public List<DadosListagemMedicos> listar() {
        return medicoRepository
                .findAll()
                .stream()
                .map(DadosListagemMedicos::new)
                .toList();
    }
}
