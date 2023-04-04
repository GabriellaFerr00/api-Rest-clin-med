package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.entities.MedicoEntity;
import clin.med.apiRestclinmed.records.AtualizarCadastroMedico;
import clin.med.apiRestclinmed.records.CadastrarMedico;
import clin.med.apiRestclinmed.records.DadosDetalhamentoMedico;
import clin.med.apiRestclinmed.records.DadosListagemMedicos;
import clin.med.apiRestclinmed.repositories.MedicoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/api/medicos")
@AllArgsConstructor
public class MedicoController {

    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid CadastrarMedico cadastrarMedico, UriComponentsBuilder uriBuilder){
        MedicoEntity medicoEntity = new MedicoEntity(cadastrarMedico);
        medicoRepository.save(medicoEntity);

        URI uri = uriBuilder.path("/api/medicos/{id}").buildAndExpand(medicoEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medicoEntity));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listarMedicos(@PageableDefault(sort = {"nome"}) Pageable paginacao ) {
        Page<DadosListagemMedicos> page = medicoRepository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedicos::new);
        //o findall devolve um page e o page tem um metodo map dentro dele
        //não precisa do tolist, pois, o map já faz a conversao  e devolve na paginacao um page do dto automaticamente

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);

        return  ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    @PutMapping
    public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizarCadastroMedico atualizar){
        MedicoEntity medico = medicoRepository.getReferenceById(atualizar.id());
        medico.atualizarInformacoes(atualizar);
        //nao precisa chamar o reposioty para salvar a alteracao, pois, eh feito automaticamente no banco de dados pela jpa
        return ResponseEntity.ok().body(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);
        medico.exclusaoLogica();

        return  ResponseEntity.noContent().build();

    }
}
