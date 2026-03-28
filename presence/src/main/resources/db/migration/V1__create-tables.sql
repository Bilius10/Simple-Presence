-- 1. Tabela de Cursos
CREATE TABLE cursos (
                        id_curso SERIAL PRIMARY KEY,
                        nome_curso VARCHAR(100) NOT NULL,
                        descricao TEXT,
                        created_by VARCHAR(100),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_by VARCHAR(100),
                        updated_at TIMESTAMP
);

-- 2. Tabela de Turmas
CREATE TABLE turmas (
                        id_turma SERIAL PRIMARY KEY,
                        nome_turma VARCHAR(50) NOT NULL,
                        id_curso INTEGER REFERENCES cursos(id_curso) ON DELETE CASCADE,
                        semestre VARCHAR(10),
                        ano INTEGER,
                        created_by VARCHAR(100),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_by VARCHAR(100),
                        updated_at TIMESTAMP
);

-- 3. Tabela de Usuários
CREATE TABLE usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          nome VARCHAR(150) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          senha VARCHAR(255) NOT NULL, -- Armazenar sempre o HASH da senha
                          tipo_usuario VARCHAR(20),
                          id_turma INTEGER REFERENCES turmas(id_turma),
                          created_by VARCHAR(100),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_by VARCHAR(100),
                          updated_at TIMESTAMP
);

-- 4. Tabela de Presença
CREATE TABLE presencas (
                           id_presenca SERIAL PRIMARY KEY,
                           id_usuario INTEGER REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
                           id_turma INTEGER REFERENCES turmas(id_turma) ON DELETE CASCADE,
                           data_aula DATE DEFAULT CURRENT_DATE,
                           status BOOLEAN NOT NULL,
                           created_by VARCHAR(100),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_by VARCHAR(100),
                           updated_at TIMESTAMP
);