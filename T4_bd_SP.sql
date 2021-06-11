create table jugada(
id_boleto int primary key AUTO_INCREMENT,
numeros_ticket varchar(30)
estado_random varchar(2),
dni varchar(8),
nombre_cliente varchar(40) not null,
direccion_cliente varchar(40) not null,
distrito_cliente varchar(30) not null,
fecha date
);


DELIMITER $$
create PROCEDURE SP_Busquedajugador(
    in _Dni varchar(8)
)
BEGIN
DECLARE res INT;
	select count(*) into res from jugada where dni like _Dni;
	IF res = 0 THEN
		select -1 as dni;
	ELSE
		select * from jugada where dni like _Dni;
	END IF;
END $$


DELIMITER $$
create PROCEDURE SP_RegistrarCliente(
IN numeros_ticket varchar(30),
IN estado_random varchar(2),
IN dni varchar(8),
IN nombre_cliente varchar(40),
IN direccion_cliente varchar(40),
IN distrito_cliente varchar(30),
IN fecha date
)
BEGIN
	INSERT INTO jugada VALUES (null,numeros_ticket , estado_random, dni, nombre_cliente, direccion_cliente, distrito_cliente, fecha);
End$$