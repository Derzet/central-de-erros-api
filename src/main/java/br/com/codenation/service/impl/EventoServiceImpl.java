package br.com.codenation.service.impl;

import br.com.codenation.exceptions.EventoNaoEncontradoException;
import br.com.codenation.model.entity.Evento;
import br.com.codenation.repository.EventoRepository;
import br.com.codenation.service.interfaces.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.codenation.service.specification.EventoSpecification.eventoEqualId;
import static br.com.codenation.service.specification.EventoSpecification.eventoLikeDescricao;
import static org.springframework.data.jpa.domain.Specification.where;


@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    @Transactional
    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Evento> listar(Integer filtroId, String filtroDescricao, Pageable pageable) {
        Specification specificationList = where(eventoLikeDescricao(filtroDescricao))
                .and(eventoEqualId(filtroId));
        return eventoRepository.findAll(specificationList,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Evento buscar(Integer eventoId) {
        return eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EventoNaoEncontradoException(eventoId));
    }
}
