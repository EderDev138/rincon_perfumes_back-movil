-- ============================================
-- RINCÓN PERFUMES - DATA.SQL Listo corrigi porque no cargaba los productos
-- ============================================
-- Archivo: data.sql
-- Ubicación: src/main/resources/data.sql
-- Corregido error(volumen_ml)
-- ============================================

-- ============================================
-- 1. ROLES
-- ============================================
INSERT INTO rol (id_rol, nombre_rol, descripcion_rol) VALUES (3, 'CLIENTE', 'Cliente del sistema')
ON DUPLICATE KEY UPDATE descripcion_rol=VALUES(descripcion_rol);

INSERT INTO rol (id_rol, nombre_rol, descripcion_rol) VALUES (2, 'ENCARGADO', 'Encargado de productos')
ON DUPLICATE KEY UPDATE descripcion_rol=VALUES(descripcion_rol);

INSERT INTO rol (id_rol, nombre_rol, descripcion_rol) VALUES (1, 'ADMIN_MAESTRO', 'Administrador maestro')
ON DUPLICATE KEY UPDATE descripcion_rol=VALUES(descripcion_rol);

-- ============================================
-- 2. USUARIO ADMIN INICIAL
-- ============================================
INSERT INTO usuario (nombre, apellido, correo, contrasena, activo) 
VALUES ('Admin', 'Sistema', 'admin@test.cl', 'admin123', true)
ON DUPLICATE KEY UPDATE activo=true;

-- ============================================
-- 3. ASIGNAR ROL ADMIN
-- ============================================
INSERT IGNORE INTO usuario_rol (id_usuario, id_rol)
SELECT 
    (SELECT id_usuario FROM usuario WHERE correo = 'admin@test.cl'),
    (SELECT id_rol FROM rol WHERE nombre_rol = 'ADMIN_MAESTRO');

-- ============================================
-- 4. TIPOS DE PRODUCTO
-- ============================================
INSERT INTO tipo_producto (nombre_tipo) VALUES ('EDP')
ON DUPLICATE KEY UPDATE nombre_tipo=VALUES(nombre_tipo);

INSERT INTO tipo_producto (nombre_tipo) VALUES ('EDT')
ON DUPLICATE KEY UPDATE nombre_tipo=VALUES(nombre_tipo);

INSERT INTO tipo_producto (nombre_tipo) VALUES ('EDC')
ON DUPLICATE KEY UPDATE nombre_tipo=VALUES(nombre_tipo);

INSERT INTO tipo_producto (nombre_tipo) VALUES ('PARFUM')
ON DUPLICATE KEY UPDATE nombre_tipo=VALUES(nombre_tipo);

-- ============================================
-- 5. GÉNEROS
-- ============================================
INSERT INTO genero (nombre_genero) VALUES ('HOMBRE')
ON DUPLICATE KEY UPDATE nombre_genero=VALUES(nombre_genero);

INSERT INTO genero (nombre_genero) VALUES ('MUJER')
ON DUPLICATE KEY UPDATE nombre_genero=VALUES(nombre_genero);

INSERT INTO genero (nombre_genero) VALUES ('UNISEX')
ON DUPLICATE KEY UPDATE nombre_genero=VALUES(nombre_genero);

-- ============================================
-- 6. CATEGORÍAS
-- ============================================
INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('ARABES', 'Perfumes árabes')
ON DUPLICATE KEY UPDATE descripcion=VALUES(descripcion);

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('EXCLUSIVAS', 'Marcas exclusivas y de nicho')
ON DUPLICATE KEY UPDATE descripcion=VALUES(descripcion);

INSERT INTO categoria (nombre_categoria, descripcion) VALUES ('DISEÑADOR', 'Marcas de diseñador reconocidas')
ON DUPLICATE KEY UPDATE descripcion=VALUES(descripcion);

-- ============================================
-- 7. MARCAS
-- ============================================
INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Dior', 'Marca de lujo francesa', 'Francia')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Chanel', 'Casa de moda francesa icónica', 'Francia')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Lattafa', 'Perfumería árabe de prestigio', 'Emiratos Árabes Unidos')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Creed', 'Perfumería de lujo exclusiva', 'Francia')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Tom Ford', 'Diseñador de moda estadounidense', 'Estados Unidos')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

INSERT INTO marca (nombre_marca, descripcion, pais_origen) 
VALUES ('Armaf', 'Marca de fragancias árabes', 'Emiratos Árabes Unidos')
ON DUPLICATE KEY UPDATE nombre_marca=VALUES(nombre_marca);

-- ============================================
-- 8. PRODUCTOS (CORREGIDO volumen_ml)
-- ============================================
-- Producto 1: Dior Sauvage
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Dior Sauvage',
  'Fragancia fresca y especiada para hombre',
  89990,
  100,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Dior'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'DISEÑADOR'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'EDP'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'HOMBRE'),
  50,
  true,
  'Fresco Especiado',
  'Aromática'
);

-- Producto 2: Chanel No 5
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Chanel No 5',
  'Icónico perfume femenino',
  125990,
  100,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Chanel'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'DISEÑADOR'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'PARFUM'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'MUJER'),
  30,
  true,
  'Floral Aldehídico',
  'Floral'
);

-- Producto 3: Lattafa Yara
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Lattafa Yara',
  'Perfume árabe dulce y cautivador',
  29990,
  100,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Lattafa'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'ARABES'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'EDP'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'MUJER'),
  100,
  true,
  'Dulce Floral',
  'Oriental'
);

-- Producto 4: Creed Aventus
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Creed Aventus',
  'Fragancia de lujo para el hombre exitoso',
  299990,
  100,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Creed'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'EXCLUSIVAS'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'EDP'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'HOMBRE'),
  20,
  true,
  'Frutal Ahumado',
  'Frutal'
);

-- Producto 5: Tom Ford Black Orchid
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Tom Ford Black Orchid',
  'Fragancia sensual unisex',
  189990,
  100,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Tom Ford'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'DISEÑADOR'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'EDP'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'UNISEX'),
  40,
  true,
  'Floral Oscuro',
  'Oriental'
);

-- Producto 6: Armaf Club de Nuit
INSERT INTO producto (nombre_producto, descripcion, precio, volumen_ml, id_marca, id_categoria, id_tipo_producto, id_genero, stock, activo, aroma, familia_olfativa)
VALUES (
  'Armaf Club de Nuit',
  'Alternativa de lujo accesible',
  39990,
  105,
  (SELECT id_marca FROM marca WHERE nombre_marca = 'Armaf'),
  (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'ARABES'),
  (SELECT id_tipo_producto FROM tipo_producto WHERE nombre_tipo = 'EDT'),
  (SELECT id_genero FROM genero WHERE nombre_genero = 'HOMBRE'),
  80,
  true,
  'Cítrico Amaderado',
  'Aromática'
);

-- ============================================
-- 9. PROMOCIÓN DE EJEMPLO
-- ============================================
INSERT INTO promociones (nombre, descripcion, porcentaje_descuento, monto_minimo, fecha_inicio, fecha_fin, activo)
VALUES (
  'Descuento por compra mayor',
  'Descuento del 10% en compras superiores a $59.990',
  10.00,
  59990.00,
  '2025-01-01',
  '2025-12-31',
  true
)
ON DUPLICATE KEY UPDATE activo=true;

-- ============================================
-- FIN DEL ARCHIVO data.sql
-- ============================================
