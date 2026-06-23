package ui;

import enums.Estado;
import enums.FormaPago;
import enums.Rol;
import exceptions.DatoInvalidoException;
import exceptions.EntidadNoEncontradaException;
import services.SistemaService;
import entities.Pedido;

import java.util.Scanner;

public class AppMenu {
    private SistemaService service = new SistemaService();
    private Scanner scanner = new Scanner(System.in);

    // MÉTODO DE PRECARGA DE DATOS
    private void precargarDatos() {
        try {
            // Precargar Categorías
            service.crearCategoria("Pizzas", "Pizzas a la piedra y de molde");
            service.crearCategoria("Bebidas", "Gaseosas, cervezas y aguas");

            // Precargar Productos (Asumiendo que Pizzas es ID 1 y Bebidas es ID 2)
            service.crearProducto("Muzzarella", 8500.0, "Pizza clásica", 50, 1L);
            service.crearProducto("Fugazzeta", 9500.0, "Pizza con cebolla y queso", 30, 1L);
            service.crearProducto("Coca-Cola 1.5L", 2500.0, "Gaseosa línea Coca-Cola", 100, 2L);

            // Precargar Usuarios
            service.crearUsuario("Ana", "Garcia", "ana.garcia@mail.com", "11223344", Rol.USUARIO, "Av. Siempre Viva", "123");
            service.crearUsuario("Carlos", "Lopez", "carlos.l@mail.com", "55443322", Rol.USUARIO, "Calle Falsa", "456");

            // Precargar un Pedido de ejemplo (Para Ana, ID 1)
            Pedido pedidoAna = service.iniciarPedido(1L);
            service.agregarDetalleAPedido(pedidoAna, 1L, 2); // 2 Muzzarellas
            service.agregarDetalleAPedido(pedidoAna, 3L, 1); // 1 Coca-Cola
            
            System.out.println("✅ Datos de prueba cargados correctamente.\n");

        } catch (Exception e) {
            System.out.println("⚠️ Error al precargar datos: " + e.getMessage());
        }
    }

    public void iniciar() {
        // LLAMAMOS AL MÉTODO ANTES DE MOSTRAR EL MENÚ
        precargarDatos();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== FOOD STORE - TPI GARELLI ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            
            try {
                String entrada = scanner.nextLine();
                if(entrada.trim().isEmpty()){
                    continue; // Vuelve a mostrar el menú si dan Enter vacío
                }
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1: menuCategorias(); break;
                    case 2: menuProductos(); break;
                    case 3: menuUsuarios(); break;
                    case 4: menuPedidos(); break;
                    case 0: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("⚠️ Opción incorrecta.");
                }
            } catch (NumberFormatException e) {
                 System.out.println("⚠️ Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }
        }
    }

    // --- MENÚ CATEGORÍAS ---
    private void menuCategorias() {
        System.out.print("\n-- CATEGORÍAS --\n1. Listar | 2. Crear | 3. Eliminar | Elija: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 1) {
                 System.out.println("\n--- Lista de Categorías ---");
                 service.listarCategorias().forEach(System.out::println);
            }
            else if (op == 2) {
                System.out.print("Nombre: "); String nom = scanner.nextLine();
                System.out.print("Descripción: "); String desc = scanner.nextLine();
                service.crearCategoria(nom, desc);
                System.out.println("✅ ¡Categoría creada!");
            }
            else if (op == 3) {
                System.out.print("ID a eliminar: ");
                service.eliminarCategoria(Long.parseLong(scanner.nextLine()));
                System.out.println("✅ ¡Eliminada correctamente (Baja Lógica)!");
            } else {
                 System.out.println("Opción no válida en Categorías.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Debe ingresar un número.");
        } catch (Exception e) { 
            System.out.println("⚠️ Error: " + e.getMessage()); 
        }
    }

    // --- MENÚ PRODUCTOS ---
    private void menuProductos() {
        System.out.print("\n-- PRODUCTOS --\n1. Listar | 2. Crear | 3. Eliminar | Elija: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 1) {
                 System.out.println("\n--- Lista de Productos ---");
                 service.listarProductos().forEach(System.out::println);
            }
            else if (op == 2) {
                System.out.print("Nombre: "); String nom = scanner.nextLine();
                System.out.print("Precio: "); Double pre = Double.parseDouble(scanner.nextLine());
                System.out.print("Stock: "); int stock = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Categoria: "); Long idCat = Long.parseLong(scanner.nextLine());
                service.crearProducto(nom, pre, "Descripción genérica", stock, idCat);
                System.out.println("✅ ¡Producto creado!");
            }
            else if (op == 3) {
                System.out.print("ID a eliminar: ");
                service.eliminarProducto(Long.parseLong(scanner.nextLine()));
                System.out.println("✅ ¡Producto Eliminado (Baja lógica)!");
            } else {
                 System.out.println("Opción no válida en Productos.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Debe ingresar valores numéricos válidos para Precio, Stock e ID.");
        } catch (Exception e) { 
            System.out.println("⚠️ Error: " + e.getMessage()); 
        }
    }

    // --- MENÚ USUARIOS ---
    private void menuUsuarios() {
        System.out.print("\n-- USUARIOS --\n1. Listar | 2. Crear | 3. Eliminar | Elija: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 1) {
                 System.out.println("\n--- Lista de Usuarios ---");
                 service.listarUsuarios().forEach(System.out::println);
            }
            else if (op == 2) {
                System.out.print("Nombre: "); String nom = scanner.nextLine();
                System.out.print("Apellido: "); String ape = scanner.nextLine();
                System.out.print("Mail: "); String mail = scanner.nextLine();
                System.out.print("Calle Dir: "); String calle = scanner.nextLine();
                System.out.print("Num Dir: "); String num = scanner.nextLine();
                service.crearUsuario(nom, ape, mail, "Sin Celular", Rol.USUARIO, calle, num);
                System.out.println("✅ ¡Usuario creado!");
            }
            else if (op == 3) {
                System.out.print("ID a eliminar: ");
                service.eliminarUsuario(Long.parseLong(scanner.nextLine()));
                System.out.println("✅ ¡Usuario Eliminado (Baja Lógica)!");
            } else {
                 System.out.println("Opción no válida en Usuarios.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Debe ingresar un número para el ID.");
        } catch (Exception e) { 
            System.out.println("⚠️ Error: " + e.getMessage()); 
        }
    }

    // --- MENÚ PEDIDOS ---
    private void menuPedidos() {
        System.out.print("\n-- PEDIDOS --\n1. Listar | 2. Crear | Elija: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 1) {
                 System.out.println("\n--- Lista de Pedidos ---");
                 service.listarPedidos().forEach(System.out::println);
            }
            else if (op == 2) {
                System.out.print("ID de Usuario comprador: ");
                Long idUsr = Long.parseLong(scanner.nextLine());
                
                Pedido p = service.iniciarPedido(idUsr);
                System.out.println("Pedido iniciado. Agregue productos:");
                
                while (true) {
                    System.out.print("ID Producto (0 para salir y cerrar pedido): ");
                    Long idProd = Long.parseLong(scanner.nextLine());
                    if (idProd == 0) break;
                    
                    System.out.print("Cantidad: ");
                    int cant = Integer.parseInt(scanner.nextLine());
                    
                    try {
                        service.agregarDetalleAPedido(p, idProd, cant);
                        System.out.println("✅ Producto agregado al pedido actual.");
                    } catch (DatoInvalidoException | EntidadNoEncontradaException e) {
                         System.out.println("⚠️ No se pudo agregar: " + e.getMessage());
                    }
                }
                System.out.println("✅ Pedido cerrado exitosamente. " + p);
            } else {
                 System.out.println("Opción no válida en Pedidos.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Error: Debe ingresar valores numéricos válidos.");
        } catch (Exception e) { 
            System.out.println("⚠️ Error en el pedido: " + e.getMessage()); 
        }
    }
}