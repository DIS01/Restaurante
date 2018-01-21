CREATE TABLE Categoria(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20),
	descripcion VARCHAR(100)
);

CREATE TABLE Item(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	valor float,
	nombre VARCHAR(100),
	descripcion VARCHAR(100),
	promo INT,
	porcentaje float
);

CREATE TABLE CategoriaItem(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	categoria int,
	item int,
	FOREIGN KEY (item) REFERENCES Item(id),
    FOREIGN KEY (categoria) REFERENCES Categoria(id)
);

CREATE TABLE Platillo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoEstimado TIME,
	item int,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE Bebida(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	marca VARCHAR(100),
	contenidolt float,
	item int,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE Combo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoPreparacion Time,
	item int,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE ComboItem(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	combo int,
	item int,
	cantidad int,
	FOREIGN KEY (item) REFERENCES Item(id),
	FOREIGN KEY (combo) REFERENCES Combo(id)	
);

CREATE TABLE Persona (
    dni INT PRIMARY KEY,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    domicilio VARCHAR(100) 
);
CREATE TABLE Rol(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tipo VARCHAR(100),
	descripcion VARCHAR(100)
);	

CREATE TABLE CuentaUsuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    persona int,
 	contrasena VARCHAR(1000),
 	rol int,
 	FOREIGN KEY (rol) REFERENCES Rol(id),
 	FOREIGN KEY (persona) REFERENCES Persona(dni)
);

CREATE TABLE Empleado(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	sueldo float,
	horario VARCHAR(1000),	
	persona iNT,
	FOREIGN KEY (persona) REFERENCES Persona(dni)	
);

CREATE TABLE Administrador(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado iNT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

CREATE TABLE Cajero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado iNT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

CREATE TABLE Cliente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	persona INT,
	fechaIncorporacion DATE,
	descuentos float,
	FOREIGN KEY (persona) REFERENCES Persona(dni)	
);

CREATE TABLE Cocinero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado iNT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

CREATE TABLE Cuenta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pagada int,
	total float,
	cliente int,
	pedido int,
	FOREIGN KEY (pedido) REFERENCES Pedido(id),
	FOREIGN KEY (cliente) REFERENCES Cliente(id)	
);
CREATE TABLE Mesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	seccion VARCHAR(100),
	numero int,
	tipo VARCHAR(100),
	asignada int
); 

CREATE TABLE Pedido(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	notificado int,
	servido int,
	horaIngreso Time,
	tiempoEstimado Time,
	estado VARCHAR(100),
	mesa int,		
	FOREIGN KEY (mesa) REFERENCES Mesa(id)	
);

CREATE TABLE PedidoDetalle(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pedido int,
	item int,
	precio float,
	cantidad int,
	detalle VARCHAR(100),
	tiempo Time,
	FOREIGN KEY (pedido) REFERENCES Pedido(id),		
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE CuentaCompartida(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pedido  int ,
	cuenta  int ,
	FOREIGN KEY (cuenta) REFERENCES Cuenta(id),	
	FOREIGN KEY (pedido) REFERENCES Pedido(id)
);

CREATE TABLE Ingrediente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	categoria VARCHAR(100),
	propiedades VARCHAR(100),
	proveedor VARCHAR(100),
	precio float
);

Create TABLE Mesero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tipomedioTransporte VARCHAR(100),
	medioPropio int,
	empleado iNT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);



CREATE TABLE Restaurante(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	direccion VARCHAR(100)
);

CREATE TABLE RestauranteEmpleado(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	restaurante int,
	empleado int,
	FOREIGN KEY (empleado) REFERENCES Empleado(id),	
	FOREIGN KEY (restaurante) REFERENCES Restaurante(id)	
);

CREATE TABLE PagoElectronico(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	identificador VARCHAR(100)
);

CREATE TABLE PagoTarjeta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numTarjeta VARCHAR(100)
);

CREATE TABLE Reporte(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	fechaInicio date,
	fechaFin date,
	solicitador int,
	tipo VARCHAR(100),
	FOREIGN KEY (solicitador) REFERENCES Administrador(id)	
);

CREATE TABLE ServicioDomicilio(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pedido int,
	domicilio VARCHAR(100),
	FOREIGN KEY (pedido) REFERENCES Pedido(id)	
);

CREATE TABLE Reservacion(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha date,
	costo float,
	numSillas int,
	numMesas int,
	cliente int,
	FOREIGN KEY (cliente) REFERENCES Cliente(id)		
);

CREATE TABLE ReservacionMesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mesa int,
	reservacion int,
	FOREIGN KEY (mesa) REFERENCES Mesa(id),	
	FOREIGN KEY (reservacion) REFERENCES Reservacion(id)			
);

Create TABLE Menu(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha Date
);

CREATE TABLE MenuPlatillos(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	platillo int,
	FOREIGN KEY (platillo) REFERENCES Platillo(id)	
);

CREATE TABLE Inventario(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	stock float,
	platillo int,
	FOREIGN KEY (platillo) REFERENCES Platillo(id)
);

CREATE TABLE HorarioEmpleado(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	dia VARCHAR(15),
	horainicio time,
	horafin time,
	empleado int,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)
);

