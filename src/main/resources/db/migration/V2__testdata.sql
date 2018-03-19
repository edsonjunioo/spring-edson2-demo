INSERT INTO avaria(descricao) VALUES('nada consta');
INSERT INTO avaria(descricao) VALUES('vencida');

INSERT INTO categoria(descricao) VALUES('nada consta');
INSERT INTO categoria(descricao) VALUES('vencida');

INSERT INTO cliente(cpf,nome,endereco,telefone) VALUES('12345678990','lara','Av: 45','13131313131');
INSERT INTO cliente(cpf,nome,endereco,telefone) VALUES('12345678991','pedro','Av: 80','46464646464');

INSERT INTO veiculo(marca, modelo, data, ano, valor, observacao) VALUES('chevrolet', 'onix', '01/01/2018','2018','50000','novo');
INSERT INTO veiculo(marca, modelo, data, ano, valor, observacao) VALUES('volks', 'gol', '01/01/2018','2018','50000','novo');

INSERT INTO multa(status,valor) VALUES('vencida', '100.00');
INSERT INTO multa(status,valor) VALUES('nada consta','0.0');

INSERT INTO funcionario(nome,cpf,endereco,telefone) VALUES('jose','12345678979','Rua; X','32313164');
INSERT INTO funcionario(nome,cpf,endereco,telefone) VALUES('maria', '12345678980','Rua; Y','32313165');

INSERT INTO locacao(data,hora) VALUES('25/05/2018', '13:00');
INSERT INTO locacao(data,hora) VALUES('11/03/2018','17:00');