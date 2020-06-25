package br.com.codenation.repository;

import br.com.codenation.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico,Integer> {
}
