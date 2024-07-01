-- Active: 1719695733864@@127.0.0.1@3306@negocio_informatica
use negocio_informatica;

INSERT INTO clientes(nombre, apellido, dni, tel_celular, email, activo) VALUES
    ('Benjamin', 'Gonzalez', '40863196', '1130947814', 'benjamin_gonzalez@hotmail.com', true),
    ('Valentina', 'Flores', '41814792', '1163273021', 'valentina_flores@hotmail.com', true),
    ('Mateo', 'Rodriguez', '42376216', '1182173010', 'mateo.rodriguez@gmail.com', true),
    ('Sofía', 'Benitez', '22798879', '1163612784', 'sofia.benitez@gmail.com', true),
    ('Julián', 'Lopez', '27864134', '1167468378', 'julian_lopez@hotmail.com', true),
    ('Olivia', 'Medina', '28745982', '1174863608', 'olivia_medina@hotmail.com', true),
    ('Gael', 'Fernandez', '40834759', '1138748764', 'gael.fernandez@gmail.com', true),
    ('Jazmín', 'Herrera', '41457692', '1187923487', 'jazmin.herrera@gmail.com', true),
    ('Enzo', 'Garcia', '42897598', '1198878422', 'enzo_garcia@hotmail.com', true),
    ('Emma', 'Pereyra', '37889245', '1176483282', 'emma_pereyra@hotmail.com', true),
    ('Valentín', 'Perez', '34387489', '1186423974', 'valentin.perez@gmail.com', true),
    ('Martina', 'Rojas', '38743764', '1143287789', 'martina.rojas@gmail.com', true),
    ('Tomás', 'Martinez', '40485798', '1188974238', 'tomas_martinez@hotmail.com', true),
    ('Malena', 'Silva', '41328478', '1178774298', 'malena_silva@hotmail.com', true),
    ('Gabriel', 'Gimenez', '42644837', '1187762346', 'gabriel.gimenez@gmail.com', true);

INSERT INTO vendedores(nombre, apellido, dni, tel_celular, email, activo) VALUES
    ('Lorenzo', 'Alvarez', '40743408', '1187482348', 'lorenzo_alvarez@hotmail.com', true),
    ('Emilia', 'Dominguez', '31874982', '1123094809', 'emilia_domiguez@hotmail.com', true),
    ('Bautista', 'Romero', '32439459', '1124072038', 'bautista.romero@gmail.com', true),
    ('Victoria', 'Castro', '25820938', '1193747623', 'victoria.castro@gmail.com', true),
    ('Felipe', 'Sosa', '26745820', '1148979572', 'felipe_sosa@hotmail.com', true),
    ('Catalina', 'Ledesma', '27892274', '1189579825', 'catalina_ledesma@hotmail.com', true),
    ('Santiago', 'Ruiz', '40463924', '1185479235', 'santiago.ruiz@gmail.com', true),
    ('Abigail', 'Ortiz', '31690284', '1135897598', 'abigail.ortiz@gmail.com', true),
    ('Ramiro', 'Acosta', '32274985', '1187359234', 'ramiro_acosta@hotmail.com', true),
    ('Amelia', 'Villalba', '26324809', '1132848747', 'amelia_villalba@hotmail.com', true);

INSERT INTO articulos(codigo, producto, color, stock, stock_minimo, stock_maximo, activo) VALUES
    (1, 'Notebook', 'Negro', 100, 10, 150, true),
    (2, 'Smartphone', 'Blanco', 40, 7, 30, true),
    (3, 'Tablet', 'Azul', 60, 4, 70, true),
    (4, 'Impresora', 'Gris', 30, 8, 35, true),
    (5, 'Monitor', 'Azul', 75, 7, 60, true),
    (6, 'Teclado', 'Negro', 40, 10, 80, true),
    (7, 'Mouse Inalámbrico', 'Gris', 20, 4, 130, true),
    (8, 'Router Inalámbrico', 'Negro', 60, 7, 30, true),
    (9, 'Parlantes', 'Azul', 80, 6, 30, true),
    (10, 'Disco Rígido', 'Gris', 25, 7, 50, true),
    (11, 'Extensor Wifi', 'Azul', 30, 10, 25, true),
    (12, 'Adaptador USB', 'Blanco', 80, 3, 60, true),
    (13, 'Web Cam', 'Negro', 50, 2, 80, true);

INSERT INTO facturas(letra, numero, fecha, monto, id_cliente, legajo_vendedor, activo) VALUES
    ('A', 1, '2024-01-01', 13000.00, 3, 5, true),
    ('B', 1, '2024-02-04', 10000.00, 2, 3, true),
    ('C', 2, '2024-03-09', 15000.00, 6, 8, true),
    ('A', 3, '2024-01-10', 11000.00, 2, 5, true),
    ('A', 2, '2024-02-07', 18000.00, 7, 8, true),
    ('B', 5, '2024-03-21', 14000.00, 2, 10, true),
    ('C', 1, '2024-01-04', 12000.00, 7, 6, true),
    ('B', 3, '2024-02-10', 19000.00, 8, 2, true),
    ('A', 5, '2024-03-07', 17000.00, 6, 8, true),
    ('B', 2, '2024-01-15', 24000.00, 8, 2, true),
    ('C', 4, '2024-02-03', 22000.00, 9, 3, true),
    ('A', 6, '2024-03-10', 28000.00, 6, 10, true),
    ('C', 5, '2024-01-01', 26000.00, 8, 3, true),
    ('B', 4, '2024-02-06', 21000.00, 7, 2, true),
    ('C', 3, '2024-03-09', 25000.00, 3, 6, true),
    ('A', 4, '2024-01-03', 23000.00, 9, 4, true);

INSERT INTO ventas(id_factura, id_articulo, letra, numero, codigo, cantidad, activo) VALUES
    (1, 1, 'A', 1, 5, 5, true),
    (2, 2, 'B', 2, 3, 3, true),
    (3, 3, 'C', 1, 6, 1, true),
    (4, 4, 'A', 2, 9, 4, true),
    (5, 5, 'A', 3, 10, 2, true),
    (6, 6, 'B', 1, 8, 3, true),
    (7, 7, 'B', 4, 2, 10, true),
    (8, 8, 'C', 3, 13, 4, true),
    (9, 9, 'C', 5, 4, 3, true),
    (10, 10, 'A', 2, 5, 2, true),
    (11, 11, 'B', 3, 11, 4, true),
    (12, 12, 'C', 1, 4, 10, true),
    (13, 13, 'C', 4, 1, 2, true);