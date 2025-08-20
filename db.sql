drop database if exists db;
create database db;
use db;

create table Productos(
	idProducto integer auto_increment,
    Nombre varchar (64),
    Precio varchar (64),
    Stock varchar (64),
    constraint pk_producto primary key (idProducto)
);
 
insert into Productos (Nombre, Precio, Stock)
values

('Pasta Dental', '20.00', '50'),
('Jabon', '10.00', '50'),
('Salsa Picante', '6.00', '75');

select * from Productos;