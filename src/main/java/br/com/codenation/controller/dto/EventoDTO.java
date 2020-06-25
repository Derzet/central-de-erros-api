package br.com.codenation.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    private Integer id;
    private String descricao;
    private String nomeServico;
}

