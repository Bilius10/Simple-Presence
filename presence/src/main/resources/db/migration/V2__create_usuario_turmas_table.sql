-- Migration: cria tabela de associação entre usuários e turmas

CREATE TABLE IF NOT EXISTS usuarios_turmas (
    id_usuario_turma SERIAL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_turma INTEGER NOT NULL,
    created_by VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_usuarios_turmas_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    CONSTRAINT fk_usuarios_turmas_turma FOREIGN KEY (id_turma) REFERENCES turmas (id_turma)
);

CREATE INDEX IF NOT EXISTS idx_usuarios_turmas_usuario ON usuarios_turmas (id_usuario);
CREATE INDEX IF NOT EXISTS idx_usuarios_turmas_turma ON usuarios_turmas (id_turma);

