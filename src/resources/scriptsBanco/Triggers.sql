create or replace function atualizar_faturas()
returns trigger AS $atualizar_faturas$
declare
	dia_vencimento int;
	proximo_mes timestamp;
	diferenca_meses int;
	aux_ano int;
	aux_mes int;
	aux_dataFim date;
begin
	aux_dataFim = (old.data_matricula + interval '1 year');
	proximo_mes = old.data_matricula + interval '1 month';
	diferenca_meses = (select ((extract('years' from aux_dataFim)::int - extract('years' from old.data_inicio)::int) * 12) - extract('month' from  old.data_inicio)::int + extract('month' from aux_dataFim)::int);

	for contador in 0 .. (diferenca_meses - 1) by 1
	loop
		aux_ano = (extract (year from proximo_mes))::int;
		aux_mes = (extract (month from proximo_mes) + contador)::int;
		if (aux_mes::int >= 13) then
			aux_mes = aux_mes - 12;
			aux_ano = aux_ano + 1;
		end if;

        delete from mydb.faturas
        where id_matricula = old.id_matricula
        and data_vencimento = (aux_ano || '-' || aux_mes || '-' || old.dia_vencimento)::date
        and data_pagamento is null
        and data_cancelamento is null;
	end loop;
	return new;
end;
$atualizar_faturas$ LANGUAGE plpgsql;

create or replace function gerar_faturas()
returns trigger AS $gerar_faturas$
declare
	proximo_mes timestamp;
	diferenca_meses int;
	aux_ano int;
	aux_mes int;
	aux_dataFim date;
begin
	aux_dataFim = (new.data_matricula + interval '1 year');
	proximo_mes = new.data_matricula + interval '1 month';
	diferenca_meses = (select ((extract('years' from aux_dataFim)::int -  extract('years' from new.data_inicio)::int) * 12) - extract('month' from  new.data_inicio)::int + extract('month' from aux_dataFim)::int);

	for contador in 0 .. (diferenca_meses - 1) by 1
	loop
		dia_vencimento = new.dia_vencimento
		aux_ano = (extract (year from proximo_mes))::int;
		aux_mes = (extract (month from proximo_mes) + contador)::int;

		if (aux_mes::int >= 13) then
			aux_mes = aux_mes - 12;
			aux_ano = aux_ano + 1;
		end if;

        insert into mydb.faturas (id_matricula, data_vencimento, valor, data_pagamento, data_cancelamento)
        values (
            new.id_matricula,
            make_date(aux_ano, aux_mes, dia_vencimento),
            new.valor,
            null,
            null
        );
	end loop;
return new;
end;
$gerar_faturas$ LANGUAGE plpgsql;

create trigger gerar_faturas
after insert
on mydb.matriculas
for each row
execute function mydb.gerar_faturas();

create trigger atualizar_faturas
after delete
on mydb.matriculas
for each row
execute function mydb.atualizar_faturas();