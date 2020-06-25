package br.com.codenation.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 60)
    @Column(unique=true)
    private String nome;

    @NotNull
    @Size(max = 15)
    private String ip;

    @NotNull
    private String sistemaOperacional;

    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Evento> eventos;

    //Auditoria
    @CreatedDate
    @JsonIgnore
    private LocalDateTime criadoEm;

}
