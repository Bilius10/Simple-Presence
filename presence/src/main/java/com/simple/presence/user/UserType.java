package com.simple.presence.user;

public enum UserType {

    STUDENT("aluno"),
    TEACHER("professor"),
    ADMIN("admin");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
