package org.kinscript.Abarroteria.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kinscript.Abarroteria.Entity.Producto;
import org.kinscript.Abarroteria.Service.IProductoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data
public class IndexController implements Serializable {

    @Autowired
    private IProductoService productoService;

    private List<Producto> productos;
    private Producto productoSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.productos = this.productoService.listar();
        this.productos.forEach(producto -> logger.info(producto.toString()));
    }

    public void agregarProducto() {
        this.productoSeleccionado = new Producto();
    }

    public void guardarProducto() {
        logger.info("Producto a guardar: " + this.productoSeleccionado);

        if (this.productoSeleccionado.getId() == null) {
            this.productoService.guardar(this.productoSeleccionado);
            this.productos.add(this.productoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto agregado"));
        } else {
            this.productoService.guardar(this.productoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
        PrimeFaces.current().ajax().update("form-productos:mensaje", "form-productos:tabla-productos");

        this.productoSeleccionado = null;
    }

    public void eliminarProducto() {
        logger.info("Producto a eliminar: " + this.productoSeleccionado);
        this.productoService.eliminar(this.productoSeleccionado.getId());
        this.productos.remove(this.productoSeleccionado);
        this.productoSeleccionado = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado"));
        PrimeFaces.current().ajax().update("form-productos:mensaje", "form-productos:tabla-productos");
    }
}
