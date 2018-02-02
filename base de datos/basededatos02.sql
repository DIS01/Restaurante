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
	activo INT,
	FOREIGN KEY (categoria) REFERENCES Categoria(id)
);

CREATE TABLE Platillo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoEstimado float,
	item INT,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE Bebida(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	marca VARCHAR(100),
	item INT,
	FOREIGN KEY (item) REFERENCES Item(id)
);

CREATE TABLE Combo(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tiempoPreparacion float,
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
	item int,
	FOREIGN KEY (item) REFERENCES Item(id)
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

CREATE TABLE Cliente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	persona INT,
	fechaIncorporacion DATE,
	FOREIGN KEY (persona) REFERENCES Persona(dni)	
);

############################## C e n a ################################
CREATE TABLE Ambiente(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50),
	activo INT
);

CREATE TABLE TipoMesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	stock INT,
	tipo VARCHAR(100),
	asignada INT,
	numSillas INT
);

CREATE TABLE AmbienteMesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ambiente INT,
	tipoMesa INT,
	asignadas INT,
	FOREIGN KEY (ambiente) REFERENCES Ambiente(id),
	FOREIGN KEY (tipoMesa) REFERENCES TipoMesa(id)
);

CREATE TABLE Mesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id2 INT,
	ambienteMesa INT,
	disponible int,
	FOREIGN KEY (ambienteMesa) REFERENCES AmbienteMesa(id)
);

CREATE TABLE Reservacion(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha DATE,
	costo FLOAT,
	cliente INT,
	FOREIGN KEY (cliente) REFERENCES Cliente(id)
);

CREATE TABLE ReservacionMesa(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ambienteMesa INT,
	reservacion INT,
	FOREIGN KEY (ambienteMesa) REFERENCES AmbienteMesa(id),	
	FOREIGN KEY (reservacion) REFERENCES Reservacion(id)			
);

CREATE TABLE Cuenta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pagada INT,
	total FLOAT,
	cliente INT,
	mesa INT,
	mesero INT,
	FOREIGN KEY (cliente) REFERENCES Cliente(id),
	FOREIGN KEY (mesa) REFERENCES Mesa(id),
	FOREIGN KEY (mesero) REFERENCES Empleado(id)
);

CREATE TABLE Pedido(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	horaIngreso TIME,
	tiempoEstimado float,
	estado VARCHAR(100),
	cuenta INT,
	fecha date,
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
CREATE TABLE TipoPago(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tipo VARCHAR(100)
);

CREATE TABLE PagoCuenta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tipoPago INT,
	cuenta INT,
	valor float,
	FOREIGN KEY (tipoPago) REFERENCES TipoPago(id),
	FOREIGN KEY (cuenta) REFERENCES Cuenta(id)
);

CREATE TABLE PagoDineroElectronico(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	identificador VARCHAR(100),
	pagoCuenta INT,
	FOREIGN KEY (pagoCuenta) REFERENCES PagoCuenta(id)
);

CREATE TABLE PagoTarjeta(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numTarjeta VARCHAR(100),
	pagoCuenta INT,
	FOREIGN KEY (pagoCuenta) REFERENCES PagoCuenta(id)
	);

CREATE TABLE Reporte(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	fechaInicio DATE,
	fechaFin DATE,
	solicitador INT,
	tipo VARCHAR(100),
	FOREIGN KEY (solicitador) REFERENCES Empleado(id)	
);
