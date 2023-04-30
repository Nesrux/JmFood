package com.nesrux.jmfood.api.exceptionHandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ErroApi {
    /*
     * Uma classe que padroniza as exceptions da api utilzando o padrao RFC 7807
     * para padronizar todos os erros da API
     */

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timesStanp;
}
