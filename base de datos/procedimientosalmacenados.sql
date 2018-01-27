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
CREATE PROCEDURE insertarPedido(horaIngreso Time, tiempoEstimado Time, estado VARCHAR(100),  mesa int)   
BEGIN  
    INSERT INTO Pedido(estado,mesa,horaIngreso,tiempoEstimado) 
    values (estado, mesa,horaIngreso,tiempoEstimado);
END $$;

DELIMITER $$

CREATE PROCEDURE insertarPedidoDetalle(pedido int, item int, precio float, cantidad int, detalle VARCHAR(100), cuenta int)   
BEGIN  
    INSERT INTO PedidoDetalle(pedido, item,precio,cantidad,detalle,cuenta) 
    values (pedido, item,precio,cantidad,detalle,cuenta);
END $$;


