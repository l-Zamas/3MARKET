# 🛒 **3MARKET - TIENDA VIRTUAL**

# 🧩 Planteamiento del problema

Actualmente, muchas PYMES venden sus productos de forma manual o con diferentes herramientas, como mensajes de chat, cuadernos u hojas de cálculo, lo cual provoca algunas dificultades frecuentes, tales como:

- **Procesos comerciales manuales:** Las ventas, los pedidos y la atención al cliente dependen del contacto directo, lo que genera demoras y limita el alcance del negocio.

- **Información dispersa:** Los datos de productos, precios, existencias y pedidos no están centralizados, lo que ocasiona errores y desactualización.

- **Falta de control del inventario:** La ausencia de actualizaciones automáticas dificulta conocer las existencias reales y evitar ventas de productos agotados.

- **Experiencia de compra limitada:** Los clientes no cuentan con una plataforma para consultar productos, realizar compras y hacer seguimiento a sus pedidos fácilmente.

- **Administración ineficiente:** La falta de reportes e historiales confiables dificulta el seguimiento de las ventas y la toma de decisiones.

- **Control de acceso insuficiente:** La ausencia de roles y permisos definidos aumenta el riesgo de modificaciones no autorizadas en la información.

En conclusión, estas dificultades reducen la competitividad de las PYMES frente a negocios que ya venden en línea.

## ❓ Formulación del problema

### Pregunta central

¿Cómo puede una plataforma web de comercio electrónico, como 3Market, ayudar a las PYMES a digitalizar sus procesos comerciales, centralizar la gestión de productos, inventario y pedidos, y mejorar la experiencia de compra de sus clientes?

### Preguntas generales

#### Sobre la situación actual de las PYMES

1. ¿Qué dificultades enfrentan las PYMES al vender y administrar sus productos sin una plataforma digital?

2. ¿Cómo afecta la falta de información centralizada al control del inventario y de los pedidos?

3. ¿Qué limitaciones tienen los clientes cuando no pueden consultar un catálogo, comprar y hacer seguimiento a sus pedidos en línea?

#### Sobre la solución propuesta

4. ¿Qué funcionalidades debe ofrecer 3Market para que el cliente pueda registrarse, explorar productos, usar el carrito y realizar su compra de forma rápida y segura?

5. ¿Cómo puede el sistema actualizar el inventario automáticamente después de cada venta para evitar errores de stock?

6. ¿Qué herramientas necesitan el administrador y el vendedor para gestionar productos, categorías, pedidos y reportes de ventas?

7. ¿Cómo se pueden definir los roles de Administrador, Vendedor y Cliente para controlar los accesos y proteger la información?

#### Sobre el desarrollo y la calidad

8. ¿Qué tecnologías (Spring Boot, Thymeleaf, MySQL, HTML y CSS) son adecuadas para construir una plataforma segura, eficiente y escalable?

9. ¿Cómo garantizar que la plataforma sea usable, esté disponible de forma continua y funcione en computadores y dispositivos móviles?

10. ¿De qué manera los reportes de ventas y el historial de compras apoyan la toma de decisiones del negocio?

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

### Ejemplo de flujo de una petición: un cliente agrega un producto al carrito

```mermaid
flowchart TD
    A[Cliente / Navegador] -->|Petición HTTP| B[CartController]
    B -->|Llama al servicio| C[CartService]
    C -->|Valida reglas de negocio y stock| D{¿Hay stock disponible?}
    D -->|Sí| E[CartItemRepository]
    E -->|Guardar o consultar datos| F[(Base de datos MySQL)]
    F -->|Devuelve resultado| E
    E --> C
    C --> B
    B -->|Agrega datos al Model| G[Vista Thymeleaf: cart.html]
    G -->|Respuesta HTML renderizada| A
    D -->|No| H[Mostrar mensaje de error]
    H --> G

# 🛠️ Requerimientos funcionales

| Código | Requisito funcional                                                                 | Rol principal            |
|--------|-------------------------------------------------------------------------------------|--------------------------|
| RF-01  | Registrar clientes de forma autónoma                                                | Cliente                  |
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

# ⚙️ Requerimientos no funcionales

| Código | Categoría          | Requisito                                                                                                                              | Criterio de cumplimiento                                                                                                       |
|--------|--------------------|----------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| RNF-01 | **Usabilidad**     | La interfaz debe ser intuitiva y amigable, de modo que cualquier usuario pueda usar la plataforma sin capacitación previa.             | Un cliente puede registrarse, buscar un producto y completar una compra siguiendo el flujo de la interfaz, sin ayuda externa. |
| RNF-02 | **Disponibilidad** | La plataforma debe estar disponible 24/7 para garantizar el acceso continuo a los usuarios.                                            | El sistema permanece accesible en todo momento, salvo mantenimientos programados y comunicados.                                |
| RNF-03 | **Seguridad**      | El sistema debe proteger la información y los datos de los usuarios mediante autenticación y autorización.                             | El acceso a cada módulo depende del rol (Administrador, Vendedor o Cliente) y las contraseñas se almacenan cifradas.           |
| RNF-04 | **Compatibilidad** | La plataforma debe ser compatible con dispositivos móviles y computadores, ofreciendo una experiencia consistente en cualquier lugar. | La interfaz se adapta (diseño responsivo) a distintos tamaños de pantalla y funciona en los navegadores más usados.            |
