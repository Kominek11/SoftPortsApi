package br.com.softports.core.internal.common.exception;

public class NaoImplementadoException extends RuntimeException {

    public NaoImplementadoException(String tipoObjeto) {
        super(String.format("%s não implementado(a)", tipoObjeto));
    }
}