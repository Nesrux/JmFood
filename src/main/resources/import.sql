insert into cozinha (id, nome) values(1 ,'tailandesa')
insert into cozinha (id, nome) values(2 , 'Brasileira')
insert into cozinha (id, nome) values(3 , 'Japonesa')
insert into cozinha (id, nome) values(4 ,'Espanhola')
insert into cozinha (id, nome) values(5 , 'Portuguesa')
insert into cozinha (id, nome) values(6, 'Romena')
insert into cozinha (id, nome) values(7, 'teste')


insert into restaurante (nome, taxa_frete, cozinha_id) values  ('EAI BURGUER', 2.7, 2) 
insert into restaurante (nome, taxa_frete, cozinha_id) values  ('Irmaos burguers', 1.7, 2) 
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 6)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 5)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('KOREWA nandesuka', 15, 3 )


insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
