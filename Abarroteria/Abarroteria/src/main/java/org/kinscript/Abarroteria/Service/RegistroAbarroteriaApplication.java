package org.kinscript.tienda.AbarroteriaApplication;

import org.kinscript.Abarroteria.Repository.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TiendaAbarroteriaApplication implements CommandLineRunner {

    private final ProductoService service;

    public TiendaAbarroteriaApplication(ProductoService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(TiendaAbarroteriaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Inventario Tienda de Abarrotes ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar precio");
            System.out.println("5. Actualizar cantidad");
            System.out.println("6. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    service.agregarProducto(nombre, precio, cantidad);
                    System.out.println("Producto agregado!");
                }
                case 2 -> service.listarProductos().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = sc.nextLong();
                    service.buscarPorId(id)
                            .ifPresentOrElse(System.out::println,
                                    () -> System.out.println("No encontrado"));
                }

            }
            case 0 -> System.out.println("Hasta luego!");
        }
    } while (opcion != 0);
}
}