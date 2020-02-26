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