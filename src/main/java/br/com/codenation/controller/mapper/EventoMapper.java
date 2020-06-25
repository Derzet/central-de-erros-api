package br.com.codenation.controller.mapper;

import br.com.codenation.controller.dto.EventoDTO;
import br.com.codenation.model.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "servico.nome", target = "nomeServico")
    })
    EventoDTO map(Evento evento);

    List<EventoDTO> map(List<Evento> eventos);

}
