CREATE TABLE multa (
  codigo bigint(20) NOT NULL AUTO_INCREMENT,
  status varchar(50) NOT NULL,
  valor varchar(100) NOT NULL,
  PRIMARY KEY (codigo)
  /*UNIQUE KEY UK_username (username)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;