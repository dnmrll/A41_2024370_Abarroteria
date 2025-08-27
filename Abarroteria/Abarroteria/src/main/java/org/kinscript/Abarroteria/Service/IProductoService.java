package org.kinscript.Abarroteria.Service;

import org.kinscript.Abarroteria.Entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listar();
    Producto buscarPorId(Integer id);
    Producto guardar(Producto producto);
    void eliminar(Producto producto);
}
