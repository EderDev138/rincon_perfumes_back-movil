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


-- 10. Versace Bright Crystal (Mujer, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(10, 'Versace Bright Crystal', 'Floral frutal con notas de yuzu y granada.', 59990, 90, 60, 1, 3, 1, 2, 2, 'https://fimgs.net/mdimg/perfume/375x500.632.jpg');

-- 11. Versace Dylan Blue (Hombre, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(11, 'Versace Dylan Blue', 'Fougère aromático con notas acuáticas y cítricas.', 64990, 100, 80, 1, 3, 1, 2, 1, 'https://fimgs.net/mdimg/perfume/375x500.39613.jpg');

-- 12. Lattafa Khamrah (Unisex, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(12, 'Lattafa Khamrah', 'Dulce, cálido y especiado. Notas de canela y dátiles.', 35000, 100, 15, 1, 4, 3, 1, 3, 'https://fimgs.net/mdimg/perfume/375x500.75805.jpg');

-- 13. Lattafa Badee Al Oud (Unisex, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(13, 'Lattafa Badee Al Oud', 'Oud for Glory. Amaderado intenso y oscuro.', 32990, 100, 40, 1, 4, 3, 1, 3, 'https://fimgs.net/mdimg/perfume/375x500.64948.jpg');

-- 14. Lattafa Yara Moi (Mujer, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(14, 'Lattafa Yara Moi', 'Versión blanca de Yara. Melocotón y jazmín cremoso.', 28990, 100, 120, 1, 4, 3, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.76709.jpg');

-- 15. Dior Fahrenheit (Hombre, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(15, 'Dior Fahrenheit', 'Revolucionario aroma con notas de cuero y violeta.', 98990, 100, 10, 1, 1, 1, 2, 1, 'https://fimgs.net/mdimg/perfume/375x500.228.jpg');

-- 16. Chanel Chance (Mujer, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(16, 'Chanel Chance Eau Tendre', 'Floral y afrutado, muy femenino y delicado.', 139990, 100, 20, 1, 2, 1, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.14813.jpg');

-- 17. Versace Pour Homme (Hombre, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(17, 'Versace Pour Homme', 'Cítrico aromático clásico, limpio y fresco.', 55990, 100, 70, 1, 3, 1, 2, 1, 'https://fimgs.net/mdimg/perfume/375x500.2318.jpg');

-- 18. Lattafa Fakhar Black (Hombre, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(18, 'Lattafa Fakhar Black', 'Inspirado en YSL Y. Manzana, jengibre y salvia.', 24990, 100, 90, 1, 4, 3, 1, 1, 'https://fimgs.net/mdimg/perfume/375x500.70233.jpg');

-- 19. Dior J'adore (Mujer, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(19, 'Dior J''adore', 'Bouquet floral icónico y sofisticado.', 119990, 100, 35, 1, 1, 1, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.210.jpg');

-- 20. Versace Man Eau Fraiche (Hombre, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(20, 'Versace Man Eau Fraiche', 'Aroma acuático y amaderado, perfecto para el verano.', 52990, 100, 65, 1, 3, 1, 2, 1, 'https://fimgs.net/mdimg/perfume/375x500.644.jpg');

-- 21. Lattafa Ameer Al Oudh (Unisex, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(21, 'Lattafa Ameer Al Oudh Intense', 'Maderas dulces, vainilla y oud. Cálido y acogedor.', 26990, 100, 85, 1, 4, 3, 1, 3, 'https://fimgs.net/mdimg/perfume/375x500.64950.jpg');

-- 22. Dior Sauvage Elixir (Hombre, Diseñador, Parfum/Elixir)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(22, 'Dior Sauvage Elixir', 'Concentración extrema, especiado, lavanda y maderas licorosas.', 135000, 60, 25, 1, 1, 1, 3, 1, 'https://fimgs.net/mdimg/perfume/375x500.68415.jpg');

-- 23. Chanel Gabrielle (Mujer, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(23, 'Chanel Gabrielle', 'Floral luminoso compuesto por cuatro flores blancas.', 129990, 100, 30, 1, 2, 1, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.45937.jpg');

-- 24. Versace Crystal Noir (Mujer, Diseñador, EDT)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(24, 'Versace Crystal Noir', 'Fragancia sensual con notas de coco, jengibre y gardenia.', 62990, 90, 55, 1, 3, 1, 2, 2, 'https://fimgs.net/mdimg/perfume/375x500.631.jpg');

-- 25. Lattafa Raghba (Unisex, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(25, 'Lattafa Raghba', 'Dulce vainilla, azúcar e incienso. Un clásico árabe.', 22990, 100, 110, 1, 4, 3, 1, 3, 'https://fimgs.net/mdimg/perfume/375x500.17360.jpg');

-- 26. Joy by Dior (Mujer, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(26, 'Joy by Dior', 'Cítrico floral vibrante con sándalo y almizcles blancos.', 109990, 90, 40, 1, 1, 1, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.51203.jpg');

-- 27. Chanel Allure Homme Edition Blanche (Hombre, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(27, 'Chanel Allure Edition Blanche', 'Equilibrio perfecto entre limón cremoso y sándalo oriental.', 132000, 100, 20, 1, 2, 1, 1, 1, 'https://fimgs.net/mdimg/perfume/375x500.24806.jpg');

-- 28. Versace Dylan Blue Pour Femme (Mujer, Diseñador, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(28, 'Versace Dylan Blue Femme', 'Afrutado floral con grosellas negras, manzana y durazno.', 68990, 100, 50, 1, 3, 1, 1, 2, 'https://fimgs.net/mdimg/perfume/375x500.47363.jpg');

-- 29. Lattafa Ana Abiyedh Rouge (Unisex, Arabe, EDP)
INSERT INTO producto (id_producto, nombre_producto, descripcion, precio, volumen_ml, stock, activo, id_marca, id_categoria, id_tipo_producto, id_genero, imagen_url) VALUES 
(29, 'Lattafa Ana Abiyedh Rouge', 'Inspiración dulce y ambarada con azafrán y almendras.', 34990, 60, 90, 1, 4, 3, 1, 3, 'https://fimgs.net/mdimg/perfume/375x500.60337.jpg');


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
