package br.com.codenation.exceptions;

public class LogNaoEncontrandoException extends RuntimeException {
    public LogNaoEncontrandoException(Integer logId) {
        super("Log com id " + logId + " não encontrando!");
    }
}
