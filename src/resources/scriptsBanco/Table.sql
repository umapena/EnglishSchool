CREATE TABLE estados (
    id serial CONSTRAINT estados_pk PRIMARY KEY,
    nome text NOT NULL
);

CREATE TABLE cidades (
    id serial CONSTRAINT cidades_pk PRIMARY KEY,
    nome text NOT NULL,
    id_estado integer NOT NULL,
    CONSTRAINT estados_fk FOREIGN KEY(id_estado) REFERENCES estados(id) DEFERRABLE
);

CREATE TABLE bairros (
    id serial CONSTRAINT bairros_pk PRIMARY KEY,
    nome text NOT NULL,
    id_cidade integer NOT NULL,
    CONSTRAINT cidades_fk FOREIGN KEY(id_cidade) REFERENCES cidades(id) DEFERRABLE
);

CREATE TABLE enderecos (
    id serial CONSTRAINT enderecos_pk PRIMARY KEY,
    logradouro text NOT NULL,
    cep text NOT NULL,
    numero integer NOT NULL,
    id_bairro integer NOT NULL,
    CONSTRAINT bairros_fk FOREIGN KEY(id_bairro) REFERENCES bairros(id) DEFERRABLE
);

CREATE TABLE alunos (
  id serial NOT NULL CONSTRAINT aluno_pk PRIMARY KEY,
  nome text NOT NULL,
  data_nascimento date,
  cpf text NOT NULL,
  sexo char(1) CONSTRAINT alunos_sexo check (
    sexo in ('M', 'F')
  ),
  celular text,
  email text,
  id_endereco integer NOT NULL,
  CONSTRAINT enderecos_fk FOREIGN KEY(id_endereco) REFERENCES enderecos(id) DEFERRABLE
);
CREATE INDEX alunos_1 ON alunos(nome);

CREATE TABLE turmas (
  id serial NOT NULL CONSTRAINT turmas_pk PRIMARY KEY,
  nome text NOT NULL,
  nivel text NOT NULL,
  periodo text NOT NULL,
  valor numeric(9, 2) NOT NULL
);

CREATE TABLE matriculas (
  id serial CONSTRAINT matriculas_pk PRIMARY KEY,
  id_aluno integer NOT NULL,
  CONSTRAINT matriculas_f1 FOREIGN KEY(id_aluno) REFERENCES alunos(id) DEFERRABLE,
  id_turma integer NOT NULL,
  CONSTRAINT matriculas_f2 FOREIGN KEY(id_turma) REFERENCES turmas(id) DEFERRABLE,
  data_matricula date NOT NULL DEFAULT localtimestamp,
  dia_vencimento integer NOT NULL,
  data_encerramento date
);

CREATE TABLE faturas (
  id serial CONSTRAINT faturas_pk PRIMARY KEY,
  id_matricula integer NOT NULL,
  CONSTRAINT faturas_matriculas_f1 FOREIGN KEY(id_matricula) REFERENCES matriculas(id) DEFERRABLE,
  data_vencimento date NOT NULL,
  valor numeric(9, 2) NOT NULL,
  data_pagamento timestamp DEFAULT localtimestamp,
  data_cancelamento date
);