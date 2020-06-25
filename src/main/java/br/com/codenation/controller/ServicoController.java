package br.com.codenation.controller;

import br.com.codenation.model.entity.Servico;
import br.com.codenation.service.interfaces.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/servico")
@ResponseStatus(HttpStatus.OK)
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @PostMapping("/salvar")
    public Servico salvar(@RequestBody @Valid Servico servico) {
        return servicoService.salvar(servico);
    }

    @GetMapping(value = "/listar")
    public Page<Servico> listar(
            @RequestParam(required = false, defaultValue="0") Integer numeroPagina,
            @RequestParam(required = false, defaultValue="50") Integer tamanho,
            @RequestParam(required = false, defaultValue="id" ) String orderTipo,
            @RequestParam(required = false, defaultValue="asc") String ordene) {
        Sort sort = Sort.by(Sort.Direction.fromString(ordene),orderTipo);
        Pageable pageable = PageRequest.of(numeroPagina,tamanho,sort);
        return servicoService.listar(pageable);
    }

    @GetMapping(value = "/{id}")
    public Servico buscar(@PathVariable("id") Integer eventoId) {
        return servicoService.buscar(eventoId);
    }


}
