create or replace
algorithm = UNDEFINED view `vw_arrecadao_anual_tipo` as
select
    `s`.`id` as `id`,
    `s`.`tipo` as `tipo`,
    sum((case when (month(`s`.`horario_agendamento`) = 1) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_JANEIRO`,
    sum((case when (month(`s`.`horario_agendamento`) = 2) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_FEVEREIRO`,
    sum((case when (month(`s`.`horario_agendamento`) = 3) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_MARCO`,
    sum((case when (month(`s`.`horario_agendamento`) = 4) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_ABRIL`,
    sum((case when (month(`s`.`horario_agendamento`) = 5) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_MAIO`,
    sum((case when (month(`s`.`horario_agendamento`) = 6) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_JUNHO`,
    sum((case when (month(`s`.`horario_agendamento`) = 7) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_JULHO`,
    sum((case when (month(`s`.`horario_agendamento`) = 8) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_AGOSTO`,
    sum((case when (month(`s`.`horario_agendamento`) = 9) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_SETEMBRO`,
    sum((case when (month(`s`.`horario_agendamento`) = 10) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_OUTUBRO`,
    sum((case when (month(`s`.`horario_agendamento`) = 11) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_NOVEMBRO`,
    sum((case when (month(`s`.`horario_agendamento`) = 12) then `s`.`valor_orcamento` else 0 end)) as `ARRECADACAO_TOTAL_DEZEMBRO`
from
    `servico` `s`
where
    ((year(`s`.`horario_agendamento`) = year(curdate()))
    and (`s`.`status_agendamento` = 'ATIVO'))
group by
    `s`.`tipo`;
create or replace
algorithm = UNDEFINED view `vw_cliente_dados_piercing` as
select
    `c`.`id` as `id`,
    `c`.`nome` as `nome`,
    `c`.`credito_cliente` as `credito_cliente`,
    `c`.`data_cadastro` as `data_cadastro`,
    `c`.`telefone` as `telefone`,
    `c`.`email` as `email`,
    sum(`s`.`valor_orcamento`) as `TOTAL_GASTO_ANUAL_PIERCING`,
    count(`s`.`id_cliente_fk`) as `NUMERO_AGENDAMENTOS_PIERCING`
from
    (`cliente` `c`
join `servico` `s` on
    ((`c`.`id` = `s`.`id_cliente_fk`)))
where
    ((year(`s`.`horario_agendamento`) = year(curdate()))
    and (`s`.`tipo` = 'PIERCING'))
group by
    `c`.`id`;
create or replace
algorithm = UNDEFINED view `vw_cliente_dados_tattoo` as
select
    `c`.`id` as `id`,
    `c`.`nome` as `nome`,
    `c`.`credito_cliente` as `credito_cliente`,
    `c`.`data_cadastro` as `data_cadastro`,
    `c`.`telefone` as `telefone`,
    `c`.`email` as `email`,
    sum(`s`.`valor_orcamento`) as `TOTAL_GASTO_ANUAL_TATTOO`,
    count(`s`.`id_cliente_fk`) as `NUMERO_AGENDAMENTOS_TATTOO`
from
    (`cliente` `c`
join `servico` `s` on
    ((`c`.`id` = `s`.`id_cliente_fk`)))
where
    ((year(`s`.`horario_agendamento`) = year(curdate()))
    and (`s`.`tipo` = 'TATTOO'))
group by
    `c`.`id`;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `instagram` varchar(30) DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `status` varchar(30) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `credito_cliente` decimal(12,2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1566 DEFAULT CHARSET=utf8;

CREATE TABLE `entrada_caixa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao_caixa` varchar(65) DEFAULT NULL,
  `forma_pagamento` varchar(255) NOT NULL,
  `horario_operacao` datetime DEFAULT NULL,
  `porcentagem_desconto` int(11) DEFAULT NULL,
  `servico` varchar(255) NOT NULL,
  `valor` decimal(12,2) NOT NULL,
  `id_cliente_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg628yloues515qcfysc319l5g` (`id_cliente_fk`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
  `nome_role` varchar(20) NOT NULL,
  PRIMARY KEY (`nome_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `saida_caixa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(65) DEFAULT NULL,
  `forma_pagamento` varchar(255) NOT NULL,
  `horario_operacao` datetime NOT NULL,
  `valor` decimal(22,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `servico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario_agendamento` datetime NOT NULL,
  `horario_conclusao_agendamento` datetime DEFAULT NULL,
  `status_agendamento` varchar(65) NOT NULL,
  `tipo` varchar(65) NOT NULL,
  `id_cliente_fk` bigint(20) NOT NULL,
  `categoria` varchar(40) DEFAULT NULL,
  `numero_sessoes` int(11) DEFAULT NULL,
  `valor_orcamento` decimal(12,2) DEFAULT NULL,
  `valor_pago_sessao` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKchvigroslfyyyg8m5ynkfm3yj` (`id_cliente_fk`)
) ENGINE=MyISAM AUTO_INCREMENT=1211 DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `login` varchar(20) NOT NULL,
  `nome_completo` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `usuarios_roles` (
  `usuario_id` varchar(20) NOT NULL,
  `role_id` varchar(20) NOT NULL,
  KEY `FKefntoswg8cckktsk0ha1wpm0i` (`role_id`),
  KEY `FKebiaxjbamgu326glxo1fbysuh` (`usuario_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;