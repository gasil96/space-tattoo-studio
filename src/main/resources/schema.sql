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
