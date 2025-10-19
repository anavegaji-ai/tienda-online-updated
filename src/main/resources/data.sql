
INSERT INTO categorias (id, nombre) VALUES (1, 'Ropa'), (2, 'Electronica');
INSERT INTO productos (id, nombre, precio, stock) VALUES (1, 'Camisa', 49900.00, 20), (2, 'Auriculares', 129900.00, 10);
INSERT INTO categorias_productos (categoria_id, producto_id) VALUES (1,1), (2,2);
-- Note: The join table name in code is producto_categoria so insert there for H2:
INSERT INTO producto_categoria (producto_id, categoria_id) VALUES (1,1), (2,2);

-- sample cliente and direccion
INSERT INTO clientes (id, nombre, email) VALUES (1, 'Ana Vega', 'ana@example.com');
INSERT INTO direcciones (id, calle, ciudad, pais, zip, cliente_id) VALUES (1, 'Calle 1', 'Bogota', 'Colombia', '110111', 1);
