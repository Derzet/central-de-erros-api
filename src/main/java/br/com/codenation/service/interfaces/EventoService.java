package br.com.codenation.service.interfaces;

import br.com.codenation.model.entity.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventoService {

    public Evento salvar(Evento evento);

    Page<Evento> listar(Integer filtroId, String filtroDescricao,Pageable pageable);

    public Evento buscar(Integer eventoId);


}
