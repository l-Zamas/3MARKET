# 🛒 **3MARKET - TIENDA VIRTUAL**

<img width="1292" height="1218" alt="image" src="https://github.com/user-attachments/assets/69db7454-e57f-4ebe-ad25-3bbe57ff1880" />


# 🛍️ ¿Qué es 3Market?

**3Market** es una plataforma web de comercio electrónico (tienda virtual) que permite a los clientes explorar productos, agregarlos al carrito y realizar pedidos de manera rápida y segura. Al mismo tiempo ofrece a los administradores y vendedores herramientas para gestionar inventarios, categorías, clientes, pedidos y reportes de ventas.

## ¿Para qué sirve 3Market?

3Market sirve para que una PYME pueda vender en línea y administrar su operación comercial desde un solo sistema. Sus objetivos principales son:

- Digitalizar los procesos comerciales de las PYMES, llevando el catálogo, las ventas y los pedidos a un entorno web.
- Centralizar la gestión de productos, categorías, inventario, clientes y pedidos en una sola plataforma.
- Mantener el inventario actualizado de forma automática después de cada venta.
- Mejorar la experiencia de compra: navegación del catálogo, búsqueda, carrito, pago simulado y seguimiento del pedido.
- Simplificar la administración para vendedores y administradores, con flujos claros de estados de pedido.
- Apoyar la toma de decisiones mediante reportes de ventas e historial de compras por cliente.
- Proteger la información con autenticación, roles y permisos de acceso.

# 🏗️ Arquitectura del proyecto 3Market

#### Arquitectura: monolítica con patrón MVC

3Market se construye como una sola aplicación Spring Boot que contiene la interfaz, la lógica de negocio y el acceso a datos. Se compila y se despliega como una única unidad (un solo .jar) y se conecta a una base de datos MySQL.

**MVC (Modelo – Vista – Controlador).** Dentro del monolito, el código se organiza en capas con responsabilidades separadas:

| Capa            | Responsabilidad                                                         | Tecnología                 |
|-----------------|-------------------------------------------------------------------------|----------------------------|
| **Vista**       | Mostrar la interfaz al usuario (catálogo, carrito, paneles).            | Thymeleaf, HTML, CSS       |
| **Controlador** | Recibir las peticiones HTTP, invocar la lógica y devolver la vista.     | Spring MVC (`@Controller`) |
| **Modelo**      | Entidades del negocio, lógica (servicios) y persistencia (repositorios). | Java, Spring Data JPA, MySQL |

Además, se usa Spring Security para la autenticación y la autorización por roles.

#### 🗂️ Estructura de carpetas

```text
3market/
└── src/
    └── main/
        ├── java/
        │   └── com/unipacifico/_3market/
        │       ├── _3marketApplication.java      # Clase principal Spring Boot
        │       │
        │       ├── config/                       # Configuración (seguridad, web)
        │       ├── controller/                   # C: reciben peticiones y devuelven vistas
        │       ├── model/                        # M: entidades JPA
        │       ├── repository/                   # M: acceso a datos
        │       ├── service/                      # M: lógica de negocio
        │       ├── dto/                          # Objetos de transferencia de datos
        │       └── exception/                    # Manejo de errores
        │
        └── resources/
            ├── fuentes/                          # Fuentes tipográficas (usadas en reportes)
            ├── informes/                          # Informes generados o plantillas de informes
            ├── jasper/                           # Plantillas JasperReports (.jrxml / .jasper)
            ├── reportes/                         # Reportes de ventas
            ├── static/                           # V: recursos estáticos
            │   └── .gitkeep
            ├── templates/                        # V: vistas Thymeleaf
            │   └── .gitkeep
            └── application.properties            # Conexión a MySQL y configuración
```

# 📋 Requerimientos del sistema

## 🛠️ Requerimientos funcionales

| Código | Requerimiento funcional                                                             | Rol principal            |
|--------|-------------------------------------------------------------------------------------|--------------------------|
| RF-01  | Registrar clientes de forma autónoma                                               | Cliente                  |
| RF-02  | Iniciar sesión con credenciales                                                    | Todos                    |
| RF-03  | Recuperar contraseña                                                               | Todos                    |
| RF-04  | Gestionar categorías de productos                                                  | Administrador            |
| RF-05  | Crear, editar y eliminar productos                                                 | Administrador            |
| RF-06  | Consultar el catálogo de productos                                                 | Cliente                  |
| RF-07  | Buscar y filtrar productos por categoría                                           | Cliente                  |
| RF-08  | Agregar, modificar y eliminar productos del carrito                                | Cliente                  |
| RF-09  | Generar órdenes de compra                                                          | Cliente                  |
| RF-10  | Simular el pago de la orden                                                        | Cliente                  |
| RF-11  | Actualizar el inventario automáticamente tras cada venta                            | Sistema                  |
| RF-12  | Gestionar los estados del pedido (pendiente, pagado, enviado, entregado, cancelado) | Administrador / Vendedor |
| RF-13  | Consultar el historial de compras y el estado de los pedidos                       | Cliente                  |
| RF-14  | Generar reportes de ventas                                                         | Administrador / Vendedor |

## ⚙️ Requerimientos no funcionales

| Código | Categoría          | Requerimiento                                                                                                                              | Criterio de cumplimiento                                                                                                       |
|--------|--------------------|----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| RNF-01 | **Usabilidad**     | La interfaz debe ser intuitiva y amigable, de modo que cualquier usuario pueda usar la plataforma sin capacitación previa.             | Un cliente puede registrarse, buscar un producto y completar una compra siguiendo el flujo de la interfaz, sin ayuda externa. |
| RNF-02 | **Disponibilidad** | La plataforma debe estar disponible 24/7 para garantizar el acceso continuo a los usuarios.                                            | El sistema permanece accesible en todo momento, salvo mantenimientos programados y comunicados.                                |
| RNF-03 | **Seguridad**      | El sistema debe proteger la información y los datos de los usuarios mediante autenticación y autorización.                             | El acceso a cada módulo depende del rol (Administrador, Vendedor o Cliente) y las contraseñas se almacenan cifradas.           |
| RNF-04 | **Compatibilidad** | La plataforma debe ser compatible con dispositivos móviles y computadores, ofreciendo una experiencia consistente en cualquier lugar. | La interfaz se adapta (diseño responsivo) a distintos tamaños de pantalla y funciona en los navegadores más usados.            |
