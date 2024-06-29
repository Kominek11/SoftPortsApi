package br.com.softports.core.internal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Filtro {

    private String chave;
    private String operador;
    private String valor;
}
