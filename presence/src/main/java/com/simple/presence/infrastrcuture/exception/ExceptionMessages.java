package com.simple.presence.infrastrcuture.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    TOKEN_NOT_GENERATED("Erro ao gerar token"),
    TOKEN_NOT_VALID("Token inválido ou expirado"),
    USER_NOT_FOUND("Usuário não encontrado"),
    INVALID_CREDENTIALS("Credenciais inválidas"),
    ENTITY_NOT_FOUND("Entidade não encontrada"),
    EMAIL_ALREADY_EXISTS("Email já cadastrado");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

}
