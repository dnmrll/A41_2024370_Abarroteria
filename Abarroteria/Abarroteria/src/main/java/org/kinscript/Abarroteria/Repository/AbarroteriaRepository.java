package org.kinscript.Abarroteria.Repository;

import org.kinscript.tienda_abarroteria.entity.Producto;
import org.kinscript.tienda_abarroteria.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public Producto agregarProducto(String Nombre, double Precio, int Stock) {
        Producto p = new Producto(null, Nombre, Precio, Stock);
        return repo.save(p);
    }

    public List<Producto> listarProductos() {
        return repo.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public void actualizarPrecio(Long id, double nuevoPrecio) {
        repo.findById(id).ifPresent(p -> {
            p.setPrecio(nuevoPrecio);
            repo.save(p);
        });
    }

    public void actualizarCantidad(Long id, int nuevaCantidad) {
        repo.findById(id).ifPresent(p -> {
            p.setCantidad(nuevaCantidad);
            repo.save(p);
        });
    }

    public void eliminarProducto(Long id) {
        repo.deleteById(id);
    }
}