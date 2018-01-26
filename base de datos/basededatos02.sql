############################# P e d i d o s ##################################

CREATE TABLE Categoria(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20)
);

CREATE TABLE Item(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	valor FLOAT,
	nombre VARCHAR(100),
	categoria INT,
	activo INT
	FOREIGN KEY (categoria) REFERENCES Categoria(id)
);

CREATE TABLE Platillo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoEstimado TIME,
	item INT,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE Bebida(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	marca VARCHAR(100),
	item INT,
	FOREIGN KEY (item) REFERENCES Item(id)
s2);

CREATE TABLE Combo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoPreparacion TIME,
	item INT,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE ComboItem(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	combo INT,
	item INT,
	cantidadItem INT,
	FOREIGN KEY (item) REFERENCES Item(id),
	FOREIGN KEY (combo) REFERENCES Combo(id)	
);

CREATE TABLE Inventario(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	stock FLOAT,
	platillo INT,
	FOREIGN KEY (platillo) REFERENCES Platillo(id)
);


########################## A c t o r e s ###########################
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
    persona INT,
 	contrasena VARCHAR(1000),
 	rol INT,
 	activo INT,
 	FOREIGN KEY (rol) REFERENCES Rol(id),
 	FOREIGN KEY (persona) REFERENCES Persona(dni)
);

CREATE TABLE Empleado(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	persona INT,
	FOREIGN KEY (persona) REFERENCES Persona(dni)	
);

CREATE TABLE Administrador(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado INT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

Create TABLE Mesero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado INT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)
);

CREATE TABLE Cocinero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado INT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

CREATE TABLE Cajero(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	empleado INT,
	FOREIGN KEY (empleado) REFERENCES Empleado(id)	
);

CREATE TABLE Cliente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	persona INT,
	fechaIncorporacion DATE,
	FOREIGN KEY (persona) REFERENCES Persona(dni)	
);

############################## C e n a ################################
CREATE TABLE Reservacion(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha DATE,
	costo FLOAT,
	ambiente INT,
	numSillas INT,
	numMesas INT,
	cliente INT,
	FOREIGN KEY (cliente) REFERENCES Cliente(id)
	FOREIGN KEY (ambiente) REFERENCES Ambiente(id)
);

CREATE TABLE ReservacionMesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mesa INT,
	reservacion INT,
	FOREIGN KEY (mesa) REFERENCES Mesa(id),	
	FOREIGN KEY (reservacion) REFERENCES Reservacion(id)			
);

CREATE TABLE Cuenta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pagada INT,
	total FLOAT,
	cliente INT,
	FOREIGN KEY (cliente) REFERENCES Cliente(id)	
);

CREATE TABLE Ambiente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50),
	numMesas INT,
	activo INT
);

CREATE TABLE Mesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ambiente INT,
	numero INT,
	tipo VARCHAR(100),
	asignada INT
	FOREIGN KEY (ambiente) REFERENCES Ambiente(id)
);

CREATE TABLE Pedido(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cuenta INT,
	horaIngreso TIME,
	tiempoEstimado TIME,
	estado VARCHAR(100),
	mesa INT,		
	FOREIGN KEY (cuenta) REFERENCES Cuenta(id)	
);

CREATE TABLE PedidoDetalle(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pedido INT,
	item INT,
	precio FLOAT,
	cantidad INT,
	detalle VARCHAR(100),
	FOREIGN KEY (pedido) REFERENCES Pedido(id),		
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE ServicioDomicilio(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pedido INT,
	domicilio VARCHAR(100),
	FOREIGN KEY (pedido) REFERENCES Pedido(id)	
);

############################# P a g o #############################
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
	fechaInicio DATE,
	fechaFin DATE,
	solicitador INT,
	tipo VARCHAR(100),
	FOREIGN KEY (solicitador) REFERENCES Administrador(id)	
);