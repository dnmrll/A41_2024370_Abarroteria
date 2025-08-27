package org.kinscript.Abarroteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.kinscript.Abarroteria.Entity.Producto;
import org.kinscript.Abarroteria.Service.IProductoService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//@SpringBootApplication
public class RegistroProductoApplication implements CommandLineRunner {

    @Autowired
    private IProductoService ProductoService;

    private static final Logger logger = LoggerFactory.getLogger(RegistroProductoApplication.class);

    private String salto = System.lineSeparator();

    public static void main(String[] args) {
        SpringApplication.run(RegistroProductoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ejecutarAppConsola();
    }

    private void ejecutarAppConsola() {
        logger.info("++++ Inventario de Abarrotes ++++");
        Scanner consola = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            int opcion = mostrarMenu(consola);
            salir = ejecutarOpcion(consola, opcion);
            logger.info(salto);
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
                *** Menú Inventario ***
                1. Listar Productos
                2. Buscar Producto por ID
                3. Agregar Producto
                4. Modificar Producto
                5. Eliminar Producto
                6. Salir
                """);
        int opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpcion(Scanner consola, int opcion) {
        boolean salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(salto + "*** Lista de Productos ***" + salto);
                List<Producto> productos = ProductoService.listar();
                productos.forEach(p -> logger.info(p.toString() + salto));
            }
            case 2 -> {
                logger.info(salto + "*** Buscar Producto por ID ***" + salto);
                Long id = Long.parseLong(consola.nextLine());
                Optional<Producto> producto = ProductoService.buscarPorId(id);
                if (producto.isPresent()) {
                    logger.info("Producto encontrado: " + producto.get() + salto);
                } else {
                    logger.info("Producto no encontrado." + salto);
                }
            }
            case 3 -> {
                logger.info(salto + "*** Agregar Producto ***" + salto);
                logger.info("Nombre:");
                String nombre = consola.nextLine();
                logger.info("Precio:");
                Double precio = Double.parseDouble(consola.nextLine());
                logger.info("Cantidad:");
                Integer cantidad = Integer.parseInt(consola.nextLine());

                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setCantidad(cantidad);

                ProductoService.guardar(producto);
                logger.info("Producto agregado: " + producto + salto);
            }
            case 4 -> {
                logger.info(salto + "*** Modificar Producto ***" + salto);
                logger.info("Ingrese ID del producto a modificar:");
                Long id = Long.parseLong(consola.nextLine());
                Optional<Producto> optProducto = ProductoService.buscarPorId(id);
                if (optProducto.isPresent()) {
                    Producto producto = optProducto.get();

                    logger.info("Nombre:");
                    String nombre = consola.nextLine();
                    logger.info("Precio:");
                    Double precio = Double.parseDouble(consola.nextLine());
                    logger.info("Cantidad:");
                    Integer cantidad = Integer.parseInt(consola.nextLine());

                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCantidad(cantidad);

                    ProductoService.guardar(producto);
                    logger.info("Producto modificado: " + producto + salto);
                } else {
                    logger.info("Producto no encontrado." + salto);
                }
            }
            case 5 -> {
                logger.info(salto + "*** Eliminar Producto ***" + salto);
                logger.info("Ingrese ID del producto a eliminar:");
                Long id = Long.parseLong(consola.nextLine());
                Optional<Producto> producto = ProductoService.buscarPorId(Integer);
                if (producto.isPresent()) {
                    ProductoService.eliminar(Integer);
                    logger.info("Producto eliminado." + salto);
                } else {
                    logger.info("Producto no encontrado." + salto);
                }
            }
            case 6 -> {
                logger.info("Saliendo... ¡Hasta luego!" + salto);
                salir = true;
            }
            default -> logger.info("Opción inválida." + salto);
        }
        return salir;
    }
}