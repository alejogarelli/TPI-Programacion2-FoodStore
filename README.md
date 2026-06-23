# 🛒 Sistema Food Store - Trabajo Práctico Integrador

**Materia:** Programación II  
**Alumno/s:** [Tu Nombre y Apellido]  

## 📝 Descripción del Proyecto
Este proyecto es un sistema de gestión (CRUD) para un comercio de comida ("Food Store"), desarrollado en Java puro mediante el paradigma de Programación Orientada a Objetos (POO). El sistema permite gestionar Categorías, Productos, Usuarios y Pedidos, manteniendo los datos persistidos temporalmente en memoria RAM a través del uso de colecciones (`ArrayList`).

---

## ⚙️ Informe de Decisiones Técnicas y Arquitectura

### 1. Modificación del Modelo UML (Implementación Relación 1 a 1)
Para cumplir estrictamente con la consigna excluyente de implementar una **relación 1 a 1 real** (ya que el modelo original se centraba en relaciones 1 a N), se tomó la decisión de diseño de extraer los datos domiciliarios del cliente. 
Se creó una nueva entidad llamada `Direccion`. De esta forma, la clase `Usuario` posee un atributo propio (`private Direccion direccion;`), logrando una composición estricta 1 a 1 unidireccional que se inyecta y valida en el constructor.

### 2. Herencia y Baja Lógica (Soft Delete)
Se implementó una clase abstracta llamada `Base` de la cual heredan todas las entidades principales del sistema (`Categoria`, `Producto`, `Usuario` y `Pedido`). 
* Esto permitió centralizar la herencia de atributos clave como el `ID` autoincremental y el flag booleano `eliminado`.
* Gracias a este flag, cuando se elimina un objeto en el sistema, no se borra de la lista, sino que su estado pasa a `true`, manteniendo la integridad referencial y cumpliendo con el requisito de baja lógica.

### 3. Arquitectura en Capas
El proyecto fue diseñado asegurando un bajo acoplamiento, separando las responsabilidades en paquetes específicos:
* `entities`: Clases modelo del sistema.
* `services`: Lógica de negocio y manejo de colecciones (ArrayList).
* `ui`: Interfaz de usuario por consola.
* `exceptions` / `enums`: Manejo de errores personalizados y opciones fijas.

### 4. Manejo de Excepciones y Robustez
Para garantizar que el programa no colapse ante el error humano (por ejemplo, introducir letras en lugar de números), la interfaz de consola (`AppMenu`) está protegida con bloques `try-catch`. 
Además, se crearon excepciones propias (ej. `EntidadNoEncontradaException` o `DatoInvalidoException`) que se lanzan desde los servicios para proteger las reglas del negocio (como evitar registrar un stock negativo).

### 5. Polimorfismo e Interfaces
Se diseñó la interfaz genérica `Calculable`, obligando a la clase `Pedido` a sobreescribir el método `calcularTotal()`. Gracias a esto, cada vez que se añade un detalle al pedido, se itera sobre los ítems para sumar subtotales y, en paralelo, descontar el stock del producto principal de forma lógica.

---

## 🚀 Cómo ejecutar el proyecto
1. Clonar o descargar este repositorio como archivo `.ZIP`.
2. Importar el proyecto en Apache NetBeans IDE (o entorno de preferencia).
3. Ejecutar desde la clase `Main.java`. El sistema realizará una precarga de datos automáticos (2 categorías y varios productos) para probar fácilmente las funcionalidades CRUD.

## 🔗 Enlace al Video de Demostración
https://drive.google.com/file/d/1B8HvXwiKPU3bbsDoOLy410XKIVFs00nk/view?usp=drive_link
