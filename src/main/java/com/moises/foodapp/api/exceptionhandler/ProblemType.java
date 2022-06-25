package com.moises.foodapp.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade está em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Erro de negocio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível."),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

    private String title;
    private  String uri;

    ProblemType(String path, String title) {
        this.uri = "https://foodapp.com.br" + path;
        this.title = title;
    }

}
