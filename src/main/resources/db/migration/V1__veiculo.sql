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