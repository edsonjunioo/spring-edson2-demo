CREATE TABLE locacao (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  data varchar(50) NOT NULL,
  hora varchar(50) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;