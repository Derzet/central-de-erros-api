package br.com.codenation.service.specification;

import br.com.codenation.model.entity.Log;
import br.com.codenation.model.entity.Log_;
import org.springframework.data.jpa.domain.Specification;

public class LogSpecification {

    public static Specification<Log> logEqualId(Integer filtroId) {
        return (filtroId != null) ?
                (log, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(log.get(Log_.id), filtroId) : null;
    }

    public static Specification<Log> logLikeMensagem(String mensagem) {
        return (mensagem != null) ?
                (log, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(log.get(Log_.mensagem), "%" + mensagem + "%") : null;
    }

    public static Specification<Log> logLikeNomeLinguagem(String nomeLinguagem) {
        return (nomeLinguagem != null) ?
                (log, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(log.get(Log_.mensagem), "%" + nomeLinguagem + "%") : null;
    }

    public static Specification<Log> logEqualsLevel(String level) {
        return (level != null) ?
                (log, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(log.get(Log_.mensagem), level) : null;
    }

    public static Specification<Log> logGreaterThanData(String data) {
        return (data != null) ?
                (log, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(log.get(Log_.mensagem), data) : null;
    }


}
