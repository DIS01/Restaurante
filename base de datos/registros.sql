INSERT INTO Categoria(nombre,descripcion) values("Platillos de entrada","");
INSERT INTO Categoria(nombre,descripcion) values("Platos Fuerte","");
INSERT INTO Categoria(nombre,descripcion) values("Bebidas","");
INSERT INTO Categoria(nombre,descripcion) values("Postres","");
INSERT INTO Categoria(nombre,descripcion) values("Combo","");

# platillos de entrada
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.00,"Croquetas de arroz","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.3,"Champignones al escabeche","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.4,"Salpicón de salmón","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.3,"Torre de Fiambre","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.34,"Huevos rellenos al curry","",1,10);

INSERT INTO Platillo(tiempoEstimado,item) values ('00:05:00',1);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:05:00',2);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:05:00',3);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:15:00',4);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:10:00',5);

INSERT INTO CategoriaItem(categoria,item) values (1,1);
INSERT INTO CategoriaItem(categoria,item) values (1,2);
INSERT INTO CategoriaItem(categoria,item) values (1,3);
INSERT INTO CategoriaItem(categoria,item) values (1,4);
INSERT INTO CategoriaItem(categoria,item) values (1,5);

# platos fuertes
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.99,"aguado de pollo","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.99,"arroz con camaron","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.00,"Arroz con menestra y lentejas","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.00,"arroz marinero","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.00,"carne colorada","",1,10);

INSERT INTO Platillo(tiempoEstimado,item) values ('00:20:00',6);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:30:00',7);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:10:00',8);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:20:00',9);
INSERT INTO Platillo(tiempoEstimado,item) values ('00:25:00',10);

INSERT INTO CategoriaItem(categoria,item) values (2,6);
INSERT INTO CategoriaItem(categoria,item) values (2,7);
INSERT INTO CategoriaItem(categoria,item) values (2,8);
INSERT INTO CategoriaItem(categoria,item) values (2,9);
INSERT INTO CategoriaItem(categoria,item) values (2,10);

# bebidas
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.00,"Tequila blanco","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (4.00,"Whisky dorado","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (5.00,"Tequila reposado","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.00,"Tequila anejo","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (4.00,"Whisky Azul","",1,10);

INSERT INTO Bebida(marca,contenidolt,item) values("R. Gonzalez.",1.5,11);
INSERT INTO Bebida(marca,contenidolt,item) values("J. W.",2.0,12);
INSERT INTO Bebida(marca,contenidolt,item) values("R. Gonzalez.",2.5,13);
INSERT INTO Bebida(marca,contenidolt,item) values("R. Gonzalez.",1.0,14);
INSERT INTO Bebida(marca,contenidolt,item) values("J. W.",1.0,15);

INSERT INTO CategoriaItem(categoria,item) values (3,11);
INSERT INTO CategoriaItem(categoria,item) values (3,12);
INSERT INTO CategoriaItem(categoria,item) values (3,13);
INSERT INTO CategoriaItem(categoria,item) values (3,14);
INSERT INTO CategoriaItem(categoria,item) values (3,15);

# postres
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (4.00,"Cheese Cake de Guayaba","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.00,"Fondant de Chocolate","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.00,"Merengue con Almendras","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (2.90,"Crujiente de Avellana","",1,10);
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (3.00,"Tiramisú de Café","",1,10);

INSERT INTO Platillo(tiempoEstimado,item) values("00:05:00",16);
INSERT INTO Platillo(tiempoEstimado,item) values("00:05:00",17);
INSERT INTO Platillo(tiempoEstimado,item) values("00:05:00",18);
INSERT INTO Platillo(tiempoEstimado,item) values("00:05:00",19);
INSERT INTO Platillo(tiempoEstimado,item) values("00:05:00",20);

INSERT INTO CategoriaItem(categoria,item) values (4,16);
INSERT INTO CategoriaItem(categoria,item) values (4,17);
INSERT INTO CategoriaItem(categoria,item) values (4,18);
INSERT INTO CategoriaItem(categoria,item) values (4,19);
INSERT INTO CategoriaItem(categoria,item) values (4,20);

# combo
INSERT INTO Item(valor,nombre,descripcion,promo,porcentaje) values (30.0,"miercoles loco","",1,10);

INSERT INTO Combo(tiempoPreparacion,item) values ("00:20:00",21);

INSERT INTO CategoriaItem(categoria,item) values (5,21);

INSERT INTO ComboItem(combo,item,cantidad) values(1,3,1);
INSERT INTO ComboItem(combo,item,cantidad) values(1,6,1);
INSERT INTO ComboItem(combo,item,cantidad) values(1,7,1);
INSERT INTO ComboItem(combo,item,cantidad) values(1,2,1);

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

INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261383,2,"mesero1","12345");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261384,2,"mesero2","12346");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261385,3,"cajero1","123457");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261386,3,"cajero2","123458");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261387,1,"admin1","1234569");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261388,1,"admin2","1234510");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261389,4,"cocinero1","1234511");
INSERT INTO CuentaUsuario(persona,rol,username,contrasena) values (0923261390,4,"cocinero2","1234512");

INSERT INTO Empleado(sueldo,horario,persona) values(360.00,"Lunes,Martes,Miercoles",0923261383);
INSERT INTO Empleado(sueldo,horario,persona) values(360.00,"Jueves,Viernes,Sabado",0923261384);
INSERT INTO Empleado(sueldo,horario,persona) values(380.00,"Lunes,Martes,Miercoles",0923261385);
INSERT INTO Empleado(sueldo,horario,persona) values(380.00,"Jueves,Viernes,Sabado",0923261386);
INSERT INTO Empleado(sueldo,horario,persona) values(400.00,"Lunes,Martes,Miercoles",0923261387);
INSERT INTO Empleado(sueldo,horario,persona) values(400.00,"Jueves,Viernes,Sabado",0923261388);
INSERT INTO Empleado(sueldo,horario,persona) values(360.00,"Lunes,Martes,Miercoles",0923261389);
INSERT INTO Empleado(sueldo,horario,persona) values(360.00,"Jueves,Viernes,Sabado",0923261390);

INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Lunes",'08:00:00','12:00:00',1);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Martes",'08:00:00','12:00:00',1);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Jueves",'08:00:00','12:00:00',2);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Viernes",'08:00:00','12:00:00',2);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Lunes",'08:00:00','12:00:00',3);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Martes",'08:00:00','12:00:00',3);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Jueves",'08:00:00','12:00:00',4);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Viernes",'08:00:00','12:00:00',4);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Lunes",'08:00:00','12:00:00',5);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Martes",'08:00:00','12:00:00',5);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Jueves",'08:00:00','12:00:00',6);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Viernes",'08:00:00','12:00:00',6);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Lunes",'08:00:00','12:00:00',7);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Martes",'08:00:00','12:00:00',7);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Jueves",'08:00:00','12:00:00',8);
INSERT INTO HorarioEmpleado(dia,horainicio,horafin,empleado) values ("Viernes",'08:00:00','12:00:00',8);

INSERT INTO Mesero(tipomedioTransporte, medioPropio,empleado) values ("moto",1,1);
INSERT INTO Mesero(tipomedioTransporte, medioPropio,empleado) values ("camioneta",1,2);

INSERT INTO Cajero(empleado) values (3);
INSERT INTO Cajero(empleado) values (4);

INSERT INTO Administrador(empleado) values(5);
INSERT INTO Administrador(empleado) values(6);

INSERT INTO Cocinero(empleado) values(7);
INSERT INTO Cocinero(empleado) values(8);



