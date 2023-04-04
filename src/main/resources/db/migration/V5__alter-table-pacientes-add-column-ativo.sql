alter table pacientes_tb add ativo tinyint;

update pacientes_tb set ativo = 1;