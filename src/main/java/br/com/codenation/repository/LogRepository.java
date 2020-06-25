package br.com.codenation.repository;

import br.com.codenation.model.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log,Integer>, JpaSpecificationExecutor<Log> {
}
