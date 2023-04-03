package clin.med.apiRestclinmed.controller;

import clin.med.apiRestclinmed.records.CadastrarMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrarMedico(@RequestBody CadastrarMedico cadastrarMedico){}
}
