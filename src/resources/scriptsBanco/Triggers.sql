DELIMITER $$
USE `public`$$
CREATE TRIGGER apagar_endereco AFTER DELETE ON alunos FOR EACH ROW
BEGIN
		delete from enderecos where id = old.id_endereco;
END;$$

DELIMITER ;