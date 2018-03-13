CREATE TABLE cliente (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  cpf varchar(50) NOT NULL,
  nome varchar(100) NOT NULL,
  endereco varchar(50) DEFAULT NULL,
  telefone varchar(50) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE KEY UK_username (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;