package br.com.codenation.controller;

import br.com.codenation.controller.dto.EventoDTO;
import br.com.codenation.controller.mapper.EventoMapper;
import br.com.codenation.model.entity.Evento;
import br.com.codenation.service.interfaces.EventoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@RestController
@RequestMapping("/evento")
@ResponseStatus(HttpStatus.OK)
public class EventoController {

    @Autowired
    EventoService eventoService;
    @Autowired
    EventoMapper eventoMapper;

    @ApiOperation(value = "salva um Evento", response = Evento.class, authorizations = {
            @Authorization(value = "evento_auth", scopes = {
                    @AuthorizationScope(scope = "write:evento", description = "salva um evento")
            })
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = Evento.class),
            @ApiResponse(code = 400, message = "Entrada inválida", response = Void.class)
    })
    @PostMapping("/salvar")
    public Evento salvar(@RequestBody @Valid Evento evento) {
        return eventoService.salvar(evento);
    }


    @ApiOperation(value = "listar Evento por DTO", response = EventoDTO.class, authorizations = {
            @Authorization(value = "evento_auth", scopes = {
                    @AuthorizationScope(scope = "write:evento", description = "lista um evento")
            })
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
    })
    @GetMapping(value = "/listar")
    public Page<EventoDTO> listar(
            @RequestParam(required = false) Integer filtroId,
            @RequestParam(required = false) String filtroDescricao,
            @RequestParam(required = false, defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "50") Integer tamanho,
            @RequestParam(required = false, defaultValue = "id") String orderTipo,
            @RequestParam(required = false, defaultValue = "asc") String ordene) {
        Sort sort = Sort.by(Sort.Direction.fromString(ordene), orderTipo);
        Pageable pageable = PageRequest.of(numeroPagina, tamanho, sort);
        Page<Evento> eventos = eventoService.listar(filtroId, filtroDescricao, pageable);
        Page<EventoDTO> eventosDTO = new PageImpl<>(eventoMapper.map(eventos.getContent()), pageable, eventos.getTotalElements());
        return eventosDTO;
    }

    @ApiOperation(value = "busca evento pelo ID", response = Evento.class, authorizations = {
            @Authorization(value = "evento_auth", scopes = {
                    @AuthorizationScope(scope = "write:evento", description = "busca evento detalhado pelo Id")
            })
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = Evento.class),
            @ApiResponse(code = 404, message = "Evento nao encontrado!", response = Void.class),
    })
    @GetMapping(value = "/{id}")
    public Evento buscar(
            @ApiParam(value = "ID de Evento usado como filtro de busca.")
            @PathVariable("id") Integer eventoId) {
        return eventoService.buscar(eventoId);
    }


}
