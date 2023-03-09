Use Liga;

DROP PROCEDURE fichaje;
/* DROP FUNCTION golesEquipo;  */
DROP TABLE IF EXISTS Goles;
DROP TABLE IF EXISTS Jugadores;
DROP TABLE IF EXISTS Partidos;
DROP TABLE IF EXISTS Equipos;
DROP TABLE IF EXISTS Presidentes;

CREATE TABLE Presidentes (
	Dni char(9),
	Nombre varchar(40) NOT NULL,
	Apellidos varchar(40) NOT NULL,
	Fecha_nac date,
	Anno YEAR NOT NULL,
	CONSTRAINT pk_presidentes PRIMARY KEY (Dni)
);

CREATE TABLE Equipos (
	Codigo char(5),
	Nombre varchar(40) NOT NULL,
	Estadio varchar(40) NOT NULL,
	Aforo integer,
	Fundacion date,
	Ciudad varchar(20) NOT NULL,
	Presidente char(9) NOT NULL,
	CONSTRAINT pk_equipos PRIMARY KEY (Codigo),
	CONSTRAINT fk_equiposPresidentes FOREIGN KEY (Presidente)
		REFERENCES Presidentes (Dni)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Partidos (
	Codigo char(4),
	Fecha date NOT NULL,
	Equipo_local char(5) NOT NULL,
	Equipo_visitante char(5) NOT NULL,
	CONSTRAINT pk_partidos PRIMARY KEY (Codigo),
	CONSTRAINT fk_partidosEquipos1 FOREIGN KEY (Equipo_local)
		REFERENCES Equipos(Codigo)
		ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT fk_partidosEquipos2 FOREIGN KEY (Equipo_visitante)
		REFERENCES Equipos(Codigo)
		ON UPDATE CASCADE ON DELETE RESTRICT
);
	
CREATE TABLE Jugadores (
	Codigo char(5),
	Nombre varchar(40) NOT NULL,
	Fecha date NOT NULL,
	Posicion enum('portero', 'defensa', 'centrocampista', 'delantero') NOT NULL,
	Cod_equipo char(5) NOT NULL,
	CONSTRAINT pk_jugadores PRIMARY KEY (Codigo),
	CONSTRAINT fk_jugadoresEquipos FOREIGN KEY (Cod_equipo)
		REFERENCES Equipos(Codigo)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Goles (
	Cod_partido char (4),
	Minuto TINYINT,
	Descripcion text,
	Cod_jugador char(5) NOT NULL,
	PropiaPuerta boolean NOT NULL,
	CONSTRAINT pk_goles PRIMARY KEY (Cod_partido, minuto),
	CONSTRAINT fk_golesPartidos FOREIGN KEY (Cod_partido)
		REFERENCES Partidos(Codigo)
			ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_golesJugadores FOREIGN KEY (Cod_jugador)
		REFERENCES Jugadores(Codigo)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO Presidentes VALUES
	('11111111A','Rodrigo','Asensio','1980-01-01','2000'),
	('22222222B','Macarena','Molero','1985-11-03','2010'),
	('33333333C','Ismael','Robles','1975-08-30','2005'),
	('44444444D','Aniceto','Morales','1960-10-12','2008');

INSERT INTO Equipos VALUES
	('EQ001','Real Estepa', 'La Paz',1000,'2000-01-01','El Ejido','11111111A'),
	('EQ002','FC Carrión', 'Nobleza',1200,'1998-06-05','Dalías','22222222B'),
	('EQ003','Atlético Zamora', 'Respeto',990,'1990-04-04','Berja','33333333C'),
	('EQ004','Palomo Sport', 'Los Pajaritos',1300,'2008-09-09','Roquetas','44444444D');

INSERT INTO Jugadores VALUES
	('J0001','Julito','2016-09-01','Portero','EQ001'),
	('J0002','Arteaga','2016-09-01','Defensa','EQ001'),
	('J0003','Moreno','2016-09-01','Centrocampista','EQ001'),
	('J0004','Cansino','2014-09-01','Delantero','EQ002'),
	('J0005','Monete','2015-09-01','Portero','EQ002'),
	('J0006','Chileno','2016-09-01','Defensa','EQ002'),
	('J0007','Postigo','2013-09-01','Delantero','EQ003'),
	('J0008','Manel','2013-09-01','Delantero','EQ003'),
	('J0009','Sorete','2014-09-01','Defensa','EQ003'),
	('J0010','Pincho','2014-09-01','Centrocampista','EQ004'),
	('J0011','Cañita','2014-09-01','Portero','EQ004'),
	('J0012','Pucelano','2013-09-01','Defensa','EQ004');

INSERT INTO Partidos VALUES
	('J112','2016-10-02','EQ001','EQ002'),
	('J134','2016-10-02','EQ003','EQ004'),
	('J231','2016-10-09','EQ003','EQ001'),
	('J242','2016-10-09','EQ004','EQ002');

INSERT INTO Goles VALUES
	('J112',10,'De penalti','J0001', false),
	('J112',12,'Falta directa','J0004', false),
	('J112',45,'Rabona','J0003', false),
	('J112',60,'Desde fuera del área','J0003', false),
	('J112',63,'Penalti','J0004', false),
	('J112',70,'Bajo las piernas del portero','J0003', false),
	('J134',15,'Hasta la cocina','J0007', false),
	('J134',20,'Penalti','J0008', false),
	('J134',80,'Cabeza en corner','J0008', false),
	('J231',75,'Penalti','J0007', false),
	('J231',13,'De tacón','J0008', false),
	('J231',66,'La mano de dios','J0003', false),
	('J231',92,'Un auténtico desastre','J0009', true),
	('J242',89,'Último cartucho','J0011', false),
	('J242',5,'Despiste','J0012', true),
	('J242',15,'En plancha','J0004', false),
	('J242',45,'Chilena','J0004', false);

DELIMITER //
CREATE PROCEDURE fichaje (j char(5), e char(5), goles int)
BEGIN
	DECLARE golesConseguidos integer default 0;
	SELECT count(*) INTO golesConseguidos FROM Goles WHERE Cod_Jugador = j;
	IF (golesConseguidos >= goles)
	THEN UPDATE Jugadores SET Cod_Equipo = e WHERE Codigo=j;
	END IF;
END;
//
/*
CREATE FUNCTION golesEquipo (e char(5)) RETURNS integer
BEGIN
	DECLARE nGoles integer default 0;
	SELECT count(*)
	INTO nGoles
	FROM Goles, Jugadores
	WHERE Goles.Cod_Jugador = Jugadores.Codigo
		AND Jugadores.Cod_Equipo = e;
	RETURN nGoles;
END;
//
*/
DELIMITER ;
