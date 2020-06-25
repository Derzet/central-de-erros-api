package br.com.codenation.model.entity;

import br.com.codenation.model.entity.converter.LevelJPAConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String mensagem;

    @NotNull
    private String nomeLinguagem;

    @NotNull
    @Convert(converter = LevelJPAConverter.class)
    private Level level;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    //Auditoria
    @CreatedDate
    @JsonIgnore
    private LocalDateTime criadoEm;

}
