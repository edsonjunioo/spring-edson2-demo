CREATE TABLE avaria (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(50) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE categoria (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(50) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cliente (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  cpf varchar(50) NOT NULL,
  nome varchar(100) NOT NULL,
  endereco varchar(50) DEFAULT NULL,
  telefone varchar(50) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE KEY UK_username (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE funcionario (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  cpf varchar(50) NOT NULL,
  endereco varchar(50) DEFAULT NULL,
  telefone varchar(50) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE KEY UK_username (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE locacao (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  data varchar(50) NOT NULL,
  hora varchar(50) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE multa (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  status varchar(50) NOT NULL,
  valor varchar(100) NOT NULL,
  PRIMARY KEY (codigo)
  /*UNIQUE KEY UK_username (username)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE veiculo (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  marca varchar(100) NOT NULL,
  modelo varchar(50) NOT NULL,
  data varchar(50) DEFAULT NULL,
  ano varchar(50) NOT NULL,
  valor varchar(50) NOT NULL,
  observacao varchar(50) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;