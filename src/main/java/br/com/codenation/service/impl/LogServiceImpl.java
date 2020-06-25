package br.com.codenation.service.impl;

import br.com.codenation.exceptions.LogNaoEncontrandoException;
import br.com.codenation.model.entity.Log;
import br.com.codenation.repository.LogRepository;
import br.com.codenation.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.codenation.service.specification.LogSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    @Transactional
    public Log salvar(Log log) {
        return logRepository.save(log);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Log> listar(Integer filtroId, String filtroMensagem, String filtroNomeLinguagem, String filtroLevel,
                            String filtroData, Pageable pageable) {
        Specification specification = where(logEqualId(filtroId)).and(logLikeMensagem(filtroMensagem))
                .and(logLikeNomeLinguagem(filtroNomeLinguagem)).and(logEqualsLevel(filtroLevel))
                .and(logGreaterThanData(filtroData));

        return logRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Log buscar(Integer logId) {
        return logRepository.findById(logId).orElseThrow(() -> new LogNaoEncontrandoException(logId));
    }
}
