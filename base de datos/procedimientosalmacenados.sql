DELIMITER $$
CREATE PROCEDURE iniciarSesion(usename VARCHAR(100), contra VARCHAR(100)  )   
BEGIN  
    SELECT * FROM CuentaUsuario  WHERE username=usename and contrasena=contra;
END $$;

DELIMITER $$
CREATE PROCEDURE crearCliente(dni int,nombres VARCHAR(100),apellidos VARCHAR(100),domicilio VARCHAR(100), fechaIncorporacion DATE)   
BEGIN  
    INSERT INTO Persona(dni,nombres,apellidos,domicilio) values (dni,nombres,apellidos,domicilio);
    INSERT INTO Cliente(fechaIncorporacion,persona) values (fechaIncorporacion,dni);
END $$;

DELIMITER $$
CREATE PROCEDURE cuentaCliente(clienteID INT, cuentaID INT ,totalPago FLOAT )   
BEGIN  
    UPDATE Cuenta SET cliente=clienteID,pagada=1, total=totalPago WHERE cuenta=cuentaID;
END $$;

DELIMITER $$
CREATE PROCEDURE registrarPagoCuenta(tipoPagoID INT, cuentaID INT, cantidad float  )   
BEGIN  
	INSERT INTO PagoCuenta(tipoPago,cuenta,valor) values (tipoPagoID,cuentaID,cantidad);
END $$;

DELIMITER $$
CREATE PROCEDURE registrarPagoElectronico(identificadorID VARCHAR(100),pagoCuentaID INT)   
BEGIN  
	INSERT INTO PagoDineroElectronico(identificador,pagoCuenta) values (identificadorID,pagoCuentaID);
END $$;

DELIMITER $$
CREATE PROCEDURE registrarPagoTarjeta(numTarjetaID VARCHAR(100),pagoCuentaID INT)   
BEGIN  
	INSERT INTO PagoTarjeta(numTarjeta,pagoCuenta) values (numTarjetaID,pagoCuentaID);
END $$;



DELIMITER $$
CREATE PROCEDURE insertarCuentaMesero(mesa INT,idMesero INT, prioridadv INT)   
BEGIN  
    INSERT INTO Cuenta(pagada,total,mesa,mesero,prioridad) values (0,0.0,mesa,idMesero,prioridadv);
    select max(c.id) from Cuenta c;
END $$;

DELIMITER $$
CREATE PROCEDURE insertarPedidoMesero( estado VARCHAR(100), cuenta int)   
BEGIN  
    INSERT INTO Pedido(horaIngreso,tiempoEstimado,estado,cuenta,fecha) 
    values ( CURRENT_TIME(),0.0,estado,cuenta, CURDATE() );
    Select max(p.id) from Pedido p;
END $$;

DELIMITER $$
CREATE PROCEDURE insertarPedidoDetalleMesero(pedido int, item1 int, precio float, cantidad int, detalle VARCHAR(100), stockactual int)   
BEGIN  
    INSERT INTO PedidoDetalle(pedido, item,precio,cantidad,detalle) 
    values (pedido, item1,precio,cantidad,detalle);
    UPDATE Inventario SET stock=stockactual where item=item1; 
END $$;

DELIMITER $$
CREATE PROCEDURE actualizarPedidoCuenta(cuentaID INT,pedidoID INT ,tiempoEstimado1 FLOAT , total1 float)   
BEGIN  
    UPDATE Pedido SET tiempoEstimado=tiempoEstimado1 WHERE id=pedidoID;
    UPDATE Cuenta SET total=total1 where id=cuentaID;
END $$;

