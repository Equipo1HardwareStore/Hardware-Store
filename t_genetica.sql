create database T_Generica;
use T_Generica;

create table Clientes(
cedula_cliente bigint(20) primary key,
direcion_cliente varchar(255),
email_cliente varchar(255),
nombre_cliente varchar(255),
telefono_clientes varchar(255)
);

create table Usuarios(
cedula_usuario bigint(20) primary key,
email_usuario varchar (255),
nombre_usuario varchar(255),
password varchar(255),
usuario varchar(255)
);

create table Ventas(
codigo_venta bigint(20) primary key auto_increment,
cedula_cliente bigint(20),
cedula_usuario bigint(20),
ivaventa double,
total_venta double,
valor_venta double,
foreign key (cedula_cliente) references Clientes (cedula_cliente),
foreign key (cedula_usuario) references Usuarios (cedula_usuario)
);

create table Proveedores(
nitproveedor varchar(255) primary key,
ciudad_proveedor varchar(255),
direccion_proveedor varchar(255),
nombre_proveedor varchar (255),
telefono_proveedor varchar(255)
);

create table Productos(
codigo_producto bigint(20) primary key,
ivacompra double,
nitproveedor varchar(255),
nombre_producto varchar(255),
precio_compra double,
precio_venta double,
foreign key (nitproveedor) references Proveedores (nitproveedor)
);

create table detalle_venta(
codigo_detalle_venta bigint(20) primary key auto_increment,
cantidad_producto int(11),
codigo_producto bigint (20),
codigo_venta bigint(20),
valor_total double,
valor_venta double,
valoriva double,
foreign key (codigo_producto) references Productos (codigo_producto),
foreign key (codigo_venta) references Ventas (codigo_venta)
)

create trigger t_fill_ventas
    after insert
    on
    detalle_venta
    for each row
    update Ventas set total_venta=total_venta + NEW.valor_total,
                      ivaventa=ivaventa+(NEW.valor_total*(NEW.valoriva/100)),
                      valor_venta=valor_venta+NEW.valor_total+(NEW.valor_total*(NEW.valoriva/100))
    where codigo_venta = NEW.codigo_venta;
