use negocio_informatica;

-- 1. Cantidad de clientes que tiene el negocio:
SELECT COUNT(*) 'cantidad de clientes' FROM clientes;

-- 2. Clientes cuyo apellido termina con 'z':
SELECT id_cliente, nombre, apellido FROM clientes WHERE apellido LIKE '%z';

-- 3. Vendedores cuya segunda letra es una 'a':
SELECT id_vendedor, nombre, apellido FROM vendedores WHERE nombre LIKE '_a%';

-- 4. Nombre y apellido, tanto de clientes como de vendedores, cuya dirección de email es de Gmail:
SELECT nombre, apellido, email FROM clientes WHERE email LIKE '%@gmail.com'
UNION
SELECT nombre, apellido, email FROM vendedores WHERE email LIKE '%@gmail.com';

-- 5. Articulos que tienen la mayor cantidad de stock:
SELECT * FROM articulos WHERE stock = (SELECT MAX(stock) FROM articulos);

