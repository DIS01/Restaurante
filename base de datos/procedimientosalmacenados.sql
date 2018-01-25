DELIMITER $$
CREATE PROCEDURE iniciarSesion(usename VARCHAR(100), contrasena VARCHAR(100)  )   
BEGIN  
    SELECT * FROM CuentaUsuario  WHERE username=usename and contrasena=contrasena;
END $$;
DELIMITER $$

CREATE PROCEDURE crearCliente(dni int,nombres VARCHAR(100),apellidos VARCHAR(100),domicilio VARCHAR(100), 
  				fechaIncorporacion DATE,descuentos float)   
BEGIN  
    INSERT INTO Persona(dni,nombres,apellidos,domicilio) values (dni,nombres,apellidos,domicilio);
    INSERT INTO Cliente(fechaIncorporacion,descuentos,persona) values (fechaIncorporacion,descuentos,dni);
END $$;

DELIMITER $$
CREATE PROCEDURE insertarPedido(notificado int, servido int, horaIngreso Time, tiempoEstimado Time, mesa int)   
BEGIN  
    INSERT INTO Pedido(notificado,servido,horaIngreso,tiempoEstimado,mesa) 
    values (notificado,servido,horaIngreso,tiempoEstimado,mesa);
END $$;

DELIMITER $$

CREATE PROCEDURE insertarPedido(pedido int, item int, precio float, cantidad int, detalle VARCHAR(100), tiempo Time)   
BEGIN  
    INSERT INTO PedidoDetalle(pedido, item,precio,cantidad,detalle,tiempo) 
    values (pedido, item,precio,cantidad,detalle,tiempo);
END $$;


