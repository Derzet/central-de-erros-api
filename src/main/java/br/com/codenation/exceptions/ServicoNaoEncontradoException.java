package br.com.codenation.exceptions;

public class ServicoNaoEncontradoException extends RuntimeException {

    public ServicoNaoEncontradoException(Integer servicoId) {
        super("Servico com id " + servicoId + " não encontrando!");
    }
}
