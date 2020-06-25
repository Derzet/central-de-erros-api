package br.com.codenation.controller;


import br.com.codenation.model.entity.Log;
import br.com.codenation.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping("/salvar")
    public Log salvar(@RequestBody @Valid Log log) {
        return logService.salvar(log);
    }

    @GetMapping(value = "/listar")
    public Page<Log> listar(
            @RequestParam(required = false) Integer filtroId,
            @RequestParam(required = false) String filtroMensagem,
            @RequestParam(required = false) String filtroNomeLinguagem,
            @RequestParam(required = false) String filtroLevel,
            @RequestParam(required = false) String filtroData,
            @RequestParam(required = false, defaultValue="0") Integer numeroPagina,
            @RequestParam(required = false, defaultValue="50") Integer tamanho,
            @RequestParam(required = false, defaultValue="id" ) String orderTipo,
            @RequestParam(required = false, defaultValue="asc") String ordene) {

        Sort sort = Sort.by(Sort.Direction.fromString(ordene),orderTipo);
        Pageable pageable = PageRequest.of(numeroPagina,tamanho,sort);
        Page<Log> logs = logService.listar(filtroId,filtroMensagem,filtroNomeLinguagem,filtroLevel,filtroData,pageable);
        return logs;
    }

    @GetMapping(value = "/{id}")
    public Log buscar(@PathVariable("id") Integer logId) {
        return logService.buscar(logId);
    }


}
