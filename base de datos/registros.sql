INSERT INTO Rol(tipo,descripcion) values("Administrador","encargado de organizar mesas, sillas");
INSERT INTO Rol(tipo,descripcion) values("Mesero","encargado de atender a ls clientes y entregar pedidos a domicilio");
INSERT INTO Rol(tipo,descripcion) values("Cajero","encargado de cobrar los pedidos");
INSERT INTO Rol(tipo,descripcion) values("Cocinero","encargado de preparar los platillos");

INSERT INTO Persona Values (0923261382,"Mauricio David","Leiton Lazaro","data de villamil");
INSERT INTO Persona Values (0923261383,"Empleado 1","Mesero 1","sauces 1");
INSERT INTO Persona Values (0923261384,"Empleado 2","Mesero 2","sauces 2");
INSERT INTO Persona Values (0923261385,"Empleado 3","Cajero 1","sauces 3");
INSERT INTO Persona Values (0923261386,"Empleado 4","Cajero 2","sauces 4");
INSERT INTO Persona Values (0923261387,"Empleado 5","Administrador 1","sauces 5");
INSERT INTO Persona Values (0923261388,"Empleado 6","Administrador 2","sauces 6");
INSERT INTO Persona Values (0923261389,"Empleado 7","Cocinero 1","sauces 7");
INSERT INTO Persona Values (0923261390,"Empleado 8","Cocinero 2","sauces 8");
INSERT INTO Persona Values (0923261391,"Cliente 1","Cliente 1","sauces 9");
INSERT INTO Persona Values (0923261392,"Cliente 2","Cliente 2","sauces 10");

INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261383,2,"mesero1","12345",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261384,2,"mesero2","12346",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261385,3,"cajero1","123457",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261386,3,"cajero2","123458",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261387,1,"admin1","1234569",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261388,1,"admin2","1234510",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261389,4,"cocinero1","1234511",1);
INSERT INTO CuentaUsuario(persona,rol,username,contrasena,activo) values (0923261390,4,"cocinero2","1234512",1);

INSERT INTO Empleado(persona) values(0923261383);
INSERT INTO Empleado(persona) values(0923261384);
INSERT INTO Empleado(persona) values(0923261385);
INSERT INTO Empleado(persona) values(0923261386);
INSERT INTO Empleado(persona) values(0923261387);
INSERT INTO Empleado(persona) values(0923261388);
INSERT INTO Empleado(persona) values(0923261389);
INSERT INTO Empleado(persona) values(0923261390);

INSERT INTO Cliente(persona,fechaIncorporacion) Values(0923261391,'2012-01-01');
INSERT INTO Cliente(persona,fechaIncorporacion) Values(0923261392,'2012-01-01');

INSERT INTO Categoria(nombre) values("Platillos de entrada");
INSERT INTO Categoria(nombre) values("Platos Fuerte");
INSERT INTO Categoria(nombre) values("Bebidas");
INSERT INTO Categoria(nombre) values("Postres");
INSERT INTO Categoria(nombre) values("Combo");

# platillos de entrada
INSERT INTO Item(valor,nombre,categoria,activo) values (2.00,"Croquetas de arroz",1,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.3,"Champignones al escabeche",1,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.4,"Salpicón de salmón",1,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.3,"Torre de Fiambre",1,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.34,"Huevos rellenos al curry",1,1);

INSERT INTO Platillo(tiempoEstimado,item) values (3.0,1);
INSERT INTO Platillo(tiempoEstimado,item) values (4.0,2);
INSERT INTO Platillo(tiempoEstimado,item) values (5.0,3);
INSERT INTO Platillo(tiempoEstimado,item) values (6.0,4);
INSERT INTO Platillo(tiempoEstimado,item) values (7.0,5);

INSERT INTO Inventario(stock, item) values(5.0,1); 
INSERT INTO Inventario(stock, item) values(10.0,2);
INSERT INTO Inventario(stock, item) values(15.0,3);
INSERT INTO Inventario(stock, item) values(5.0,4);
INSERT INTO Inventario(stock, item) values(10.0,5);

# platos fuertes
INSERT INTO Item(valor,nombre,categoria,activo) values (2.99,"aguado de pollo",2,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.99,"arroz con camaron",2,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.00,"Arroz con menestra y lentejas",2,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.00,"arroz marinero",2,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.00,"carne colorada",2,1);

INSERT INTO Platillo(tiempoEstimado,item) values (5.0,6);
INSERT INTO Platillo(tiempoEstimado,item) values (6.0,7);
INSERT INTO Platillo(tiempoEstimado,item) values (7.0,8);
INSERT INTO Platillo(tiempoEstimado,item) values (8.0,9);
INSERT INTO Platillo(tiempoEstimado,item) values (9.0,10);

INSERT INTO Inventario(stock, item) values(4.0,6); 
INSERT INTO Inventario(stock, item) values(9.0,7);
INSERT INTO Inventario(stock, item) values(12.0,8);
INSERT INTO Inventario(stock, item) values(4.0,9);
INSERT INTO Inventario(stock, item) values(9.0,10);
# bebidas
INSERT INTO Item(valor,nombre,categoria,activo) values (3.00,"Tequila blanco",3,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (4.00,"Whisky dorado",3,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (5.00,"Tequila reposado",3,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.00,"Tequila anejo",3,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (4.00,"Whisky Azul",3,1);

INSERT INTO Bebida(marca,item) values("R. Gonzalez.",11);
INSERT INTO Bebida(marca,item) values("J. W.",12);
INSERT INTO Bebida(marca,item) values("R. Gonzalez.",13);
INSERT INTO Bebida(marca,item) values("R. Gonzalez.",14);
INSERT INTO Bebida(marca,item) values("J. W.",15);

INSERT INTO Inventario(stock, item) values(3.0,11); 
INSERT INTO Inventario(stock, item) values(8.0,12);
INSERT INTO Inventario(stock, item) values(11.0,13);
INSERT INTO Inventario(stock, item) values(3.0,14);
INSERT INTO Inventario(stock, item) values(8.0,15);

# postres
INSERT INTO Item(valor,nombre,categoria,activo) values (4.00,"Cheese Cake de Guayaba",4,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.00,"Fondant de Chocolate",4,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.00,"Merengue con Almendras",4,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (2.90,"Crujiente de Avellana",4,1);
INSERT INTO Item(valor,nombre,categoria,activo) values (3.00,"Tiramisú de Café",4,1);

INSERT INTO Platillo(tiempoEstimado,item) values(4.0,16);
INSERT INTO Platillo(tiempoEstimado,item) values(4.0,17);
INSERT INTO Platillo(tiempoEstimado,item) values(3.0,18);
INSERT INTO Platillo(tiempoEstimado,item) values(3.0,19);
INSERT INTO Platillo(tiempoEstimado,item) values(4.0,20);

INSERT INTO Inventario(stock, item) values(3.0,16); 
INSERT INTO Inventario(stock, item) values(8.0,17);
INSERT INTO Inventario(stock, item) values(11.0,18);
INSERT INTO Inventario(stock, item) values(3.0,19);
INSERT INTO Inventario(stock, item) values(8.0,20);

# combo
INSERT INTO Item(valor,nombre,categoria,activo) values (30.0,"miercoles loco",5,1);

INSERT INTO Combo(tiempoEstimado,item) values (20.0,21);

INSERT INTO ComboItem(combo,item,cantidadItem) values(1,3,1);
INSERT INTO ComboItem(combo,item,cantidadItem) values(1,6,2);
INSERT INTO ComboItem(combo,item,cantidadItem) values(1,7,1);

INSERT INTO Inventario(stock, item) values(8.0,21);
# info mesas restaurante
INSERT INTO Ambiente(nombre,activo) values("primera categoria",1);
INSERT INTO Ambiente(nombre,activo) values("segunda categoria",1);

INSERT INTO TipoMesa(stock,tipo,asignada,numSillas) VALUES (4,"familiares",0,4);
INSERT INTO TipoMesa(stock,tipo,asignada,numSillas) VALUES (3,"pareja",0,2);

INSERT INTO AmbienteMesa(ambiente,tipoMesa,asignadas) values (1,1,0);
INSERT INTO AmbienteMesa(ambiente,tipoMesa,asignadas) values (2,1,0);

INSERT INTO Mesa(id2,ambienteMesa,disponible) values(1,1,1);
INSERT INTO Mesa(id2,ambienteMesa,disponible ) values(1,2,1);

#cuenta
INSERT INTO Cuenta(pagada , total,mesa,mesero) values (0,21.0,1,1);

#pedido1
INSERT INTO Pedido(horaIngreso,tiempoEstimado,estado,cuenta,fecha ) Values("11:03:33",33.0,"Preparando",1,"2018-01-28");

INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (1,1,23.2,2,"sin azucar");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (1,2,22.2,1,"sin 1");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (1,3,20.2,1,"sin 2");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (1,4,23.2,1,"sin 3");

#pedido2
INSERT INTO Pedido(horaIngreso,tiempoEstimado,estado,cuenta,fecha ) Values("12:03:33",40.0,"Preparando",1,"2018-01-28");

INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (2,1,23.2,2,"sin 1");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (2,2,22.2,1,"sin 2");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (2,3,20.2,1,"sin 3");
INSERT INTO PedidoDetalle(pedido,item,precio,cantidad,detalle) Values (2,4,23.2,1,"sin 4");

INSERT INTO TipoPago(tipo) values ("Dinero electrónico");
INSERT INTO TipoPago(tipo) values ("Efectivo");
INSERT INTO TipoPago(tipo) values ("Tarjeta de crédito");
