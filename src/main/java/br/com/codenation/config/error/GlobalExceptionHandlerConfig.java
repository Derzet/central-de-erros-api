package br.com.codenation.config.error;

import br.com.codenation.exceptions.EventoNaoEncontradoException;
import br.com.codenation.exceptions.LogNaoEncontrandoException;
import br.com.codenation.exceptions.ServicoNaoEncontradoException;
import br.com.codenation.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlerConfig {

    @ExceptionHandler({UsuarioNaoEncontradoException.class, ServicoNaoEncontradoException.class, ServicoNaoEncontradoException.class,
            LogNaoEncontrandoException.class})
    public final ResponseEntity<ApiError> handleException(Exception excecao, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (excecao instanceof UsuarioNaoEncontradoException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UsuarioNaoEncontradoException usuarioNaoEncontradoException = (UsuarioNaoEncontradoException) excecao;
            return handleUsuarioNaoEncontradoException(usuarioNaoEncontradoException, headers, status, request);
        } else if (excecao instanceof EventoNaoEncontradoException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            EventoNaoEncontradoException eventoNaoEncontrandoException = (EventoNaoEncontradoException) excecao;
            return handleEventoNaoEncontrandoException(eventoNaoEncontrandoException, headers, status, request);
        } else if (excecao instanceof ServicoNaoEncontradoException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ServicoNaoEncontradoException servicoNaoEncontradoException = (ServicoNaoEncontradoException) excecao;
            return handleServicoNaoEncontradoException(servicoNaoEncontradoException, headers, status, request);
        } else if (excecao instanceof LogNaoEncontrandoException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            LogNaoEncontrandoException logNaoEncontrandoException = (LogNaoEncontrandoException) excecao;
            return handleLogNaoEncontrandoException(logNaoEncontrandoException, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(excecao, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiError> handleLogNaoEncontrandoException(LogNaoEncontrandoException logNaoEncontrandoException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(logNaoEncontrandoException.getMessage());
        return handleExceptionInternal(logNaoEncontrandoException, new ApiError(errors, status), headers, status, request);
    }

    private ResponseEntity<ApiError> handleServicoNaoEncontradoException(ServicoNaoEncontradoException servicoNaoEncontradoException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(servicoNaoEncontradoException.getMessage());
        return handleExceptionInternal(servicoNaoEncontradoException, new ApiError(errors, status), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException usuarioNaoEncontradoException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(usuarioNaoEncontradoException.getMessage());
        return handleExceptionInternal(usuarioNaoEncontradoException, new ApiError(errors, status), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleEventoNaoEncontrandoException(EventoNaoEncontradoException eventoNaoEncontrandoException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(eventoNaoEncontrandoException.getMessage());
        return handleExceptionInternal(eventoNaoEncontrandoException, new ApiError(errors, status), headers, status, request);
    }


    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
