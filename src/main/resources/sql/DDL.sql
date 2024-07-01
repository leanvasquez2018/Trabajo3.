-- Active: 1719695733864@@127.0.0.1@3306
-- extensión MySQL Weijan Chen

drop database if exists negocio_informatica;
create database negocio_informatica;
use negocio_informatica;
drop table if exists clientes;
drop table if exists vendedores;
drop table if exists articulos;
drop table if exists facturas;
drop table if exists ventas;


create table clientes(
    id_cliente int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni char(8) not null,
    tel_celular varchar(10),
    email varchar(50),
    activo boolean
);

create table vendedores(
    id_vendedor int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni char(8) not null,
    tel_celular varchar(10),
    email varchar(50),
    activo boolean
);

create table articulos(
    id_articulo int auto_increment primary key,
    codigo int,
    producto varchar(100) not null,
    color varchar(50) not null,
    stock int not null,
    stock_minimo int not null,
    stock_maximo int not null,
    activo boolean
);

create table facturas(
    id_factura int auto_increment primary key,
    letra enum('A','B','C') not null,
    numero int,
    fecha date not null,
    monto double not null,
    id_cliente int not null,
    legajo_vendedor int not null,
    activo boolean
);

alter table facturas
add constraint FK_facturas_clientes
foreign key(id_cliente)
references clientes(id_cliente);

alter table facturas
add constraint FK_facturas_vendedores
foreign key(legajo_vendedor)
references vendedores(id_vendedor);

create table ventas(
    id_venta int auto_increment primary key,
    id_factura int,
    id_articulo int,
    letra enum('A','B','C') not null,
    numero int not null,
    codigo int not null,
    cantidad int not null,
    activo boolean
);

alter table ventas
add constraint FK_ventas_facturas
foreign key(id_factura)
references facturas(id_factura);

alter table ventas
add constraint FK_ventas_articulos
foreign key(id_articulo)
references articulos(id_articulo);

