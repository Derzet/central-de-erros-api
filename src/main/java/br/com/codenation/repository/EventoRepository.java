package br.com.codenation.repository;

import br.com.codenation.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventoRepository extends JpaRepository<Evento,Integer>, JpaSpecificationExecutor<Evento> { }


