# 🛍️ Rincón Perfumes - Backend API REST

Proyecto académico de desarrollo de un e-commerce de perfumes con sistema de gestión de inventario, carrito de compras y procesamiento de pedidos.

---

## 👥 Equipo de Desarrollo

**Estudiantes:** Leonardo y Eder  
**Asignatura:** Desarrollo Full Stack App Móvil  
**Docente:** Fernando  
**Institución:** Duoc UC  
**Fecha:** Octubre 2025

---

## 📋 Descripción del Proyecto

Sistema backend para una tienda online de perfumes que permite:

- Registro y autenticación de usuarios (clientes, encargados y administradores)
- Catálogo de productos con búsqueda y filtros
- Carrito de compras
- Procesamiento de pedidos con cálculo automático de descuentos e IVA
- Gestión de inventario
- Panel de administración

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **MySQL 8.0**
- **Maven** (gestión de dependencias)
- **JWT** (autenticación)
- **Lombok** (reducción de código boilerplate)

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- ✅ Java JDK 17 o superior
- ✅ MySQL 8.0 o superior
- ✅ Maven 3.6+
- ✅ IDE (IntelliJ IDEA, Eclipse o VS Code)
- ✅ Postman o similar (para probar la API)

---

## ⚙️ Configuración Inicial

### 1. Clonar el Repositorio

```bash
git clone [URL_DEL_REPOSITORIO]
cd rincon_perfumes_back
```

### 2. Configurar Base de Datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE rincon_perfumes_back;
```

### 3. Configurar Credenciales

Editar el archivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rincon_perfumes_back?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

**⚠️ Importante:** Si tu MySQL tiene contraseña, colocarla después del `=`. Si no tiene contraseña, dejar vacío como se muestra arriba.

### 4. Ejecutar la Aplicación

Desde la terminal:

```bash
mvn clean install
mvn spring-boot:run
```

O desde el IDE: Ejecutar `RinconPerfumesBackApplication.java`

La aplicación estará disponible en: **http://localhost:8080**

### 5. Cargar Datos Iniciales (Opcional)

Para poblar la base de datos con datos de prueba:

```bash
mysql -u root rincon_perfumes_back < datos_iniciales.sql
```

Si tu MySQL tiene contraseña:
```bash
mysql -u root -p rincon_perfumes_back < datos_iniciales.sql
```

Esto creará:
- 3 roles (Cliente, Encargado, Admin)
- 1 usuario administrador (admin@test.cl / admin123)
- Tipos de producto, géneros, categorías y marcas básicas

---

## 🌐 Endpoints de la API

La API estará disponible en: `http://localhost:8080`

### Autenticación

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/register` | Registrar nuevo cliente |
| POST | `/api/auth/login` | Iniciar sesión |

### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Listar todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| POST | `/api/productos` | Crear producto (requiere autenticación) |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |

### Carrito

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/carrito/{clienteId}` | Ver carrito |
| POST | `/api/carrito/agregar` | Agregar producto al carrito |
| DELETE | `/api/carrito/{clienteId}` | Vaciar carrito |

### Pedidos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/pedidos/crear` | Crear pedido desde carrito |
| GET | `/api/pedidos/cliente/{id}` | Ver pedidos del cliente |
| GET | `/api/pedidos/{id}` | Detalle de pedido |

### Administración

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/admin/encargados-pendientes` | Listar encargados pendientes |
| PUT | `/api/admin/encargados/{id}/aprobar` | Aprobar encargado |
| DELETE | `/api/admin/encargados/{id}` | Eliminar encargado |

---

## 🧪 Probar la API

### Ejemplo 1: Login

```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "correo": "admin@test.cl",
    "contrasena": "admin123"
}
```

### Ejemplo 2: Listar Productos

```bash
GET http://localhost:8080/api/productos
```

### Ejemplo 3: Crear Pedido

```bash
POST http://localhost:8080/api/pedidos/crear
Authorization: Bearer [TOKEN_JWT]
Content-Type: application/json

{
    "clienteId": 1
}
```

---

## 📱 Integración con Android

Este backend está diseñado para ser consumido por una aplicación Android.

**Configuración en Android:**

- **URL Base Emulador:** `http://10.0.2.2:8080`
- **URL Base Dispositivo Físico:** `http://[TU_IP_LOCAL]:8080`

**CORS está habilitado** para permitir peticiones desde cualquier origen.

---

## 💼 Lógica de Negocio Implementada

### Cálculo de Pedidos

El sistema calcula automáticamente:

1. **Subtotal:** Suma de (precio × cantidad) de todos los productos
2. **Descuento:** 10% si el subtotal supera $59.990
3. **IVA:** 19% sobre (subtotal - descuento)
4. **Total:** Subtotal - descuento + IVA

### Gestión de Stock

- El stock se descuenta automáticamente al confirmar un pedido
- Se valida stock disponible antes de agregar al carrito
- Alerta cuando el stock es menor a 5 unidades

### Sistema de Roles

- **Cliente:** Puede comprar productos y ver sus pedidos
- **Encargado:** Además puede gestionar productos
- **Admin Maestro:** Además puede aprobar encargados y gestión completa

---

## 📁 Estructura del Proyecto

```
src/main/java/cl/perfumes/rincon_perfumes_back/
├── controller/          # Controladores REST
├── service/            # Lógica de negocio
├── repository/         # Acceso a datos (JPA)
├── model/
│   ├── entidades/      # Entidades JPA
│   └── dto/            # Objetos de transferencia
├── security/           # Configuración de seguridad y JWT
├── exception/          # Manejo de excepciones
└── util/               # Utilidades (validaciones, constantes)
```

---

## 🔐 Seguridad

- Autenticación mediante **JWT (JSON Web Tokens)**
- Validación de roles para endpoints protegidos
- Tokens con expiración de 24 horas


---

## 🐛 Troubleshooting

### Error: No se puede conectar a MySQL

**Solución:**
- Verificar que MySQL esté corriendo en el puerto 3306
- Verificar usuario y contraseña en `application.properties`
- Verificar que existe la base de datos `rincon_perfumes_back`

### Error: Puerto 8080 ya en uso

**Solución:**
Cambiar el puerto en `application.properties`:
```properties
server.port=8081
```

### Error de compilación

**Solución:**
```bash
mvn clean install
```

---

## 📝 Notas de Desarrollo

- El proyecto utiliza Spring Boot 3.x con Jakarta EE (no javax)
- La base de datos se crea automáticamente al iniciar la aplicación (`ddl-auto=update`)
- Los datos iniciales deben cargarse manualmente con el script SQL proporcionado
- Puerto de la aplicación: **8080**
- Puerto de MySQL: **3306**

---

## 📚 Recursos Adicionales

- [Documentación Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT.io](https://jwt.io/)

---



---

**Última actualización:** 24 de Octubre 2025