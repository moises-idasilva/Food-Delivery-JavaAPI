#COZINHAS
insert into food_delivery_db.cozinha (nome) values ('Tailandesa');
insert into food_delivery_db.cozinha (nome) values ('Indiana');

#RESTAURANTES
insert into food_delivery_db.restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into food_delivery_db.restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into food_delivery_db.restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

#ESTADOS
insert into food_delivery_db.estado (nome) values ('Minas Gerais');
insert into food_delivery_db.estado (nome) values ('São Paulo');
insert into food_delivery_db.estado (nome) values ('Goiás');

#CIDADES
insert into food_delivery_db.cidade (nome, estado_id) values ('Uberlândia', 1);
insert into food_delivery_db.cidade (nome, estado_id) values ('Campinas', 2);
insert into food_delivery_db.cidade (nome, estado_id) values ('Goiânia', 3);
insert into food_delivery_db.cidade (nome, estado_id) values ('Inhumas', 3);

#FORMAS DE PAGAMENTO
insert into food_delivery_db.forma_pagamento (descricao) values ('Cartão de crédito');
insert into food_delivery_db.forma_pagamento (descricao) values ('Cartão de débito');
insert into food_delivery_db.forma_pagamento (descricao) values ('Dinheiro');

#PERMISSOES DO USUARIO
insert into food_delivery_db.permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into food_delivery_db.permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

