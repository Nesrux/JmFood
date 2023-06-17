set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from restaurante_usuario_responsavel;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;
delete from foto_produto;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
INSERT INTO estado (id, nome) VALUES (4, 'Acre');
INSERT INTO estado (id, nome) VALUES (5, 'Alagoas');
INSERT INTO estado (id, nome) VALUES (6, 'Amapá');
INSERT INTO estado (id, nome) VALUES (7, 'Amazonas');
INSERT INTO estado (id, nome) VALUES (8, 'Bahia');
INSERT INTO estado (id, nome) VALUES (9, 'Ceará');
INSERT INTO estado (id, nome) VALUES (10, 'Distrito Federal');
INSERT INTO estado (id, nome) VALUES (11, 'Espírito Santo');
INSERT INTO estado (id, nome) VALUES (12, 'Goiás');
INSERT INTO estado (id, nome) VALUES (13, 'Maranhão');


insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Rio Branco', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Cruzeiro do Sul', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Sena Madureira', 4);
INSERT INTO cidade (id, nome, estado_id) VALUES (9, 'Maceió', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (10, 'Arapiraca', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (11, 'Palmeira dos Índios', 5);
INSERT INTO cidade (id, nome, estado_id) VALUES (12, 'Manaus', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (13, 'Parintins', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (14, 'Itacoatiara', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (15, 'Salvador', 7);
INSERT INTO cidade (id, nome, estado_id) VALUES (16, 'Feira de Santana', 7);
INSERT INTO cidade (id, nome, estado_id) VALUES (17, 'Vitória da Conquista', 7);
INSERT INTO cidade (id, nome, estado_id) VALUES (18, 'Fortaleza', 8);
INSERT INTO cidade (id, nome, estado_id) VALUES (19, 'Caucaia', 8);
INSERT INTO cidade (id, nome, estado_id) VALUES (20, 'Juazeiro do Norte', 8);
INSERT INTO cidade (id, nome, estado_id) VALUES (21, 'Brasília', 9);
INSERT INTO cidade (id, nome, estado_id) VALUES (22, 'Ceilândia', 9);
INSERT INTO cidade (id, nome, estado_id) VALUES (23, 'Gama', 9);
INSERT INTO cidade (id, nome, estado_id) VALUES (24, 'Vitória', 10);
INSERT INTO cidade (id, nome, estado_id) VALUES (25, 'Vila Velha', 10);
INSERT INTO cidade (id, nome, estado_id) VALUES (26, 'Serra', 10);
INSERT INTO cidade (id, nome, estado_id) VALUES (27, 'Goiânia', 11);
INSERT INTO cidade (id, nome, estado_id) VALUES (28, 'Aparecida de Goiânia', 11);
INSERT INTO cidade (id, nome, estado_id) VALUES (29, 'Anápolis', 11);
INSERT INTO cidade (id, nome, estado_id) VALUES (30, 'São Luís', 12);
INSERT INTO cidade (id, nome, estado_id) VALUES (31, 'Imperatriz', 12);
INSERT INTO cidade (id, nome, estado_id) VALUES (32, 'São José de Ribamar', 12);


insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Elma maria pinto', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'jalin rabei lanches', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'takop alnasu akara', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'fizza now hamburgueria', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'twopay pall alimentos', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (7, 'amir maei', 10, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (8, 'cleiton hasta  ', 9.50, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (9, 'OK burguer', 15, 2, UTC_TIMESTAMP, UTC_TIMESTAMP, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (10, 'lima maria lanches', 12, 3, UTC_TIMESTAMP, UTC_TIMESTAMP, true, true);


insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
INSERT INTO forma_pagamento (id, descricao) VALUES (4, 'Cartão de Crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (5, 'Dinheiro');
INSERT INTO forma_pagamento (id, descricao) VALUES (6, 'Pix');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 0, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Hambúrguer de Frango', 'Descrição 1 - Lorem Ipsum', 15.90, 1, true);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Batata Frita', 'Descrição 2 - Lorem Ipsum', 9.90, 1, true);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Pizza Margherita', 'Descrição 3 - Lorem Ipsum', 25.90, 2, true);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Lasanha à Bolonhesa', 'Descrição 4 - Lorem Ipsum', 19.90, 2, true);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Sushi Misto', 'Descrição 5 - Lorem Ipsum', 32.90, 3, true);
INSERT INTO produto (nome, descricao, preco, restaurante_id, ativo) VALUES ('Yakissoba de Frango', 'Descrição 6 - Lorem Ipsum', 24.90, 3, true);


insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp),
(6, 'Débora Mendonça', 'jmfoodmail@gmail.com', '123', utc_timestamp),
(7, 'glaucya', 'jmfoodmail@gmail.com', '123', utc_timestamp),
(8, 'Carlos Lima', 'jmfoodmail@gmail.com', '123', utc_timestamp);


insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 'f9981ca4-5a5e-4da3-af04-933861df3e55', 1, 6, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, 'd178b637-a785-4768-a3cb-aa1ce5a8cdab', 4, 7, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 8, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil',
        'CRIADO', '2023-11-01 21:10:00', '2019-10-30 21:10:45', '2019-10-30 21:55:44', 110, 10, 120);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 2, 1, 110, 110, null);


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-02 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 174.4, 5, 179.4);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 3, 2, 87.2, 174.4, null);


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (5, '8d774bcf-b238-42f3-aef1-5fb388754d63', 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins',
        'ENTREGUE', '2023-11-02 21:00:30', '2019-11-02 21:01:21', '2019-11-02 21:20:10', 87.2, 10, 97.2);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 3, 1, 87.2, 87.2, null);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (7, 'cb276bc6-4841-4da7-853f-4d44905adc44', 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-03 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 5, 999);
    
 insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (7, 7, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (8, 7, 2, 2, 110, 220, 'Menos picante, por favor');  
  
insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (8, 'ce12f244-c01d-40e5-9e90-646d1bf43b9e', 2, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-03 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 5, 999);
        
 insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (9, '2af53ecc-20ec-4d13-bb16-f5383a30c597', 3, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-04 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 9, 999);

 insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (10, 'c7183a0f-c29d-494b-8004-d1864a3f47a7', 3, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-05 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 9, 999);
     
insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (11, 'aace2828-6bc3-4d8d-b970-476bacd0cd60', 3, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-05 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 9, 999);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (12, '210cc128-1808-4310-83e1-bf3991deaa14', 3, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro',
        'ENTREGUE', '2023-11-05 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 999, 9, 999);

        