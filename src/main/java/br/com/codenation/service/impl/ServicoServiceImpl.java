package br.com.codenation.service.impl;

import br.com.codenation.exceptions.ServicoNaoEncontradoException;
import br.com.codenation.model.entity.Servico;
import br.com.codenation.repository.ServicoRepository;
import br.com.codenation.service.interfaces.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    @Transactional
    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Servico> listar(Pageable pageable) {
        return servicoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Servico buscar(Integer servicoId) {
        return servicoRepository.findById(servicoId).orElseThrow(() -> new ServicoNaoEncontradoException(servicoId));
    }
}

