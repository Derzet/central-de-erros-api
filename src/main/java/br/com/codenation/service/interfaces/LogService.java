package br.com.codenation.service.interfaces;

import br.com.codenation.model.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {
    Log salvar(Log log);

    Page<Log> listar(Integer filtroId, String filtroMensagem, String filtroNomeLinguagem, String filtroLevel, String filtroData, Pageable pageable);

    Log buscar(Integer logId);
}
