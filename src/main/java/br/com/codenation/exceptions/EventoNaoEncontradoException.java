package br.com.codenation.exceptions;

public class EventoNaoEncontradoException extends RuntimeException {

    public EventoNaoEncontradoException(Integer eventoId) {
        super("Evento com id " + eventoId + " não encontrando!");
    }

}
