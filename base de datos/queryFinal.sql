### Sin Optimizar#################################################
DELIMITER $$
CREATE PROCEDURE meserosSusCuentasTotalMayorA(valor INT)   
BEGIN
    SELECT per.nombres, per.apellidos, cue.id, cue.total, sum(pdt.cantidad) as cantidadDePlatos
    FROM Empleado emp, Persona per, Cuenta cue, Pedido ped, PedidoDetalle pdt
    WHERE emp.persona = per.dni and cue.mesero = emp.id and ped.cuenta = cue.id
    	  and pdt.pedido = ped.id and cue.total > valor
    GROUP BY cue.id
END $$;


### Optimizada OPCION 1 ##########################################
DELIMITER $$
CREATE VIEW cuentasTotalMayorA50 AS 
BEGIN
	SELECT 	Cuenta.id, 
			Cuenta.total, 
			Cuenta.mesero,
			sum(PedidoDetalle.cantidad) as cantidadDePlatos
	FROM ((Cuenta
		 INNER JOIN Pedido ON Pedido.cuenta = Cuenta.id)
		 INNER JOIN PedidoDetalle ON PedidoDetalle.pedido = Pedido.id);
	WHERE Cuenta.total > 50
	GROUP BY Cuenta.id
END $$;

DELIMITER $$
CREATE VIEW nombresApellidosMesero AS 
BEGIN
	SELECT 	Persona.nombres,
			Persona.apellidos,
			Empleado.id
	FROM (((CuentaUsuario
	INNER JOIN Rol ON CuentaUsuario.rol = rol.id)
	INNER JOIN Persona ON CuentaUsuario.persona = Persona.dni)
	INNER JOIN Empleado ON  Empleado.persona = Persona.dni);
	WHERE Rol.tipo = "Mesero"
END $$;

DELIMITER $$
CREATE PROCEDURE meserosSusCuentasTotalMayorA50()  
BEGIN		
	SELECT 	nombresApellidosMesero.nombres, 
			nombresApellidosMesero.apellidos, 
			cuentasTotalMayorA50.id, 
			cuentasTotalMayorA50.total, 
			cuentasTotalMayorA50.cantidadDePlatos
	FROM cuentasTotalMayorA50
		INNER JOIN nombresApellidosMesero 
			ON cuentasTotalMayorA50.mesero = nombresApellidosMesero.id );
END $$;

### Optimizada OPCION 2 ##########################################
DELIMITER $$
CREATE VIEW cuentasTotalMasDe50 AS 
BEGIN
	SELECT 	Persona.nombres, 
			Persona.apellidos, 
			Cuenta.id, 
			Cuenta.total, 
			sum(PedidoDetalle.cantidad) as cantidadDePlatos
	FROM ((((Cuenta
		 INNER JOIN Pedido ON Pedido.cuenta = Cuenta.id)
		 INNER JOIN PedidoDetalle ON PedidoDetalle.pedido = Pedido.id)
		 INNER JOIN Empleado ON  Cuenta.mesero = Empleado.id)
		 INNER JOIN Persona ON Empleado.persona = Persona.dni);
		 
	WHERE Cuenta.total > 50
	GROUP BY Cuenta.id
END $$;
