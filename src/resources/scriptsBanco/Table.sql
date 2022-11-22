----------------------------------------------
----------------------------------------------
-- CRIACAO DE TABELAS
----------------------------------------------
----------------------------------------------

/*********************************************
ALUNOS
*********************************************/
CREATE TABLE alunos (
  id serial NOT NULL CONSTRAINT aluno_pk PRIMARY KEY,
  nome text NOT NULL,
  data_nascimento date,
  cpf text NOT NULL,
  sexo char(1) CONSTRAINT alunos_sexo check (
    sexo in ('M', 'F')
  ),
  celular text,
  email text
);
--
--
CREATE INDEX alunos_1 ON alunos(nome);
--
--
/*********************************************
TURMAS
*********************************************/
CREATE TABLE turmas (
  id serial NOT NULL CONSTRAINT turmas_pk PRIMARY KEY,
  nome text NOT NULL,
  nivel text NOT NULL,
  periodo text NOT NULL,
  valor numeric(9, 2) NOT NULL
);


/*********************************************
MATRICULAS
*********************************************/
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


/*********************************************
FATURAS
*********************************************/
CREATE TABLE faturas (
  id serial CONSTRAINT faturas_pk PRIMARY KEY,
  id_matricula integer NOT NULL,
  CONSTRAINT faturas_matriculas_f1 FOREIGN KEY(id_matricula) REFERENCES matriculas(id) DEFERRABLE,
  data_vencimento date NOT NULL,
  valor numeric(9, 2) NOT NULL,
  data_pagamento timestamp DEFAULT localtimestamp,
  data_cancelamento date
);

