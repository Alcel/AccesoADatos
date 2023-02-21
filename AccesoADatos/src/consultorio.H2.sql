CREATE TABLE Pacientes (
    Nif varchar(20),
    Nombre varchar(40),
    Tfno varchar(40),
    constraint pk_nif primary key(Nif)
);

CREATE TABLE Doctores (
    Nif varchar(20),
    Nombre varchar(40),
    Edad Integer,
    Especialidad varchar(40) CHECK(Especialidad in ('pediatra','general','odontologo')),
    constraint pk_nif_doctores primary key(Nif),
    constraint ch_especialidad check(Especialidad in ('pediatra','general','odontologo'))
);

CREATE TABLE Visitas(
	Nif_doctor varchar(20),
	Nif_paciente varchar(20),
	Fecha date,
	constraint pk_keys primary key(Nif_doctor,Nif_paciente,Fecha),
	constraint  fk_doctor FOREIGN key(Nif_doctor) References Doctores(Nif)
	,
	constraint  fk_paciente FOREIGN key(Nif_paciente) References Pacientes(Nif)
);

INSERT INTO pacientes VALUES
    ('11111111A', 'Pedro', '611111111'),
    ('22222222B', 'Monica', '622222222'),
    ('33333333C', 'Elena', '633333333');

INSERT INTO DOCTORES VALUES
    ('66666666F', 'Holmes', 45, 'pediatra'),
    ('77777777G', 'Moriarti', 54, 'general'),
    ('88888888H', 'Watson', 54, 'odontologo');

INSERT INTO VISITAS VALUES
    ('66666666F', '11111111A', '2015-01-01'),
    ('66666666F', '22222222B', '2016-01-01'),
    ('77777777G', '11111111A', '2017-01-01'),
    ('88888888H', '33333333C', '2005-01-06');