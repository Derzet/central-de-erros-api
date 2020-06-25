package br.com.codenation.service.interfaces;

import br.com.codenation.model.entity.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicoService {

    public Servico salvar(Servico servico);

    public Page<Servico> listar(Pageable pageable);

    public Servico buscar(Integer servicoId);
}
