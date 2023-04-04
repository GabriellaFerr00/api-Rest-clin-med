package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.records.CadastrarMedico;
import clin.med.apiRestclinmed.records.DadosListagemMedicos;
import clin.med.apiRestclinmed.repositories.MedicoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<DadosListagemMedicos> listar(Pageable paginacao) {
        return medicoRepository
                .findAll(paginacao)
                .map(DadosListagemMedicos::new);
        //o findall devolve um page e o page tem um metodo map dentro dele
        //não precisa do tolist, pois, o map já faz a conversao e devolve na paginacao um page do dto automaticamente
    }
}
