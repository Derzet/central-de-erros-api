package br.com.codenation.service.specification;

import br.com.codenation.model.entity.Evento;
import br.com.codenation.model.entity.Evento_;
import org.springframework.data.jpa.domain.Specification;

public class EventoSpecification {

    public static Specification<Evento> eventoEqualId(Integer filtroId) {
        return (filtroId!=null)?
        (evento,criteriaQuery,criteriaBuilder) -> criteriaBuilder.equal(evento.get(Evento_.id),filtroId):null;
    }

    public static Specification<Evento> eventoLikeDescricao(String filtroDescricao) {
        return (filtroDescricao!=null)?
                (evento,criteriaQuery,criteriaBuilder) -> criteriaBuilder.like(evento.get(Evento_.descricao),"%"+filtroDescricao+"%"):null;
    }



}
