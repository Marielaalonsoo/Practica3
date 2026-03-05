package edu.comillas.icai.gitt.pat.spring.Practica5.Controlador;

import edu.comillas.icai.gitt.pat.spring.Practica5.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.servicio.ServicioCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorCarrito {


    @Autowired
    RepoCarrito repoCarrito;
    @Autowired
    ServicioCarrito servicioCarrito;

    @PostMapping("/api/carrito") @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@RequestBody Carrito NuevoCarrito) {

        Carrito carrito= servicioCarrito.crea(NuevoCarrito );
        return carrito;
    }

    @GetMapping("/api/carrito/{Id}")
    public Carrito lee(@PathVariable Long Id) {
        return servicioCarrito.lee(Id);

    }


    @DeleteMapping("/api/carrito/{Id}")
    public void borra(@PathVariable Long Id) {
        Carrito carrito = servicioCarrito.lee(Id);
        servicioCarrito.borra(carrito);
    }


    //añadir linea

    @PostMapping("/api/carrito/{Id}/linea")
    public Carrito anadirLinea(@RequestBody LineaCarrito lineaCarrito, @PathVariable Long Id) {
        Carrito carrito = servicioCarrito.crearlinea(lineaCarrito,Id);
        return carrito;
    }

    //borrar linea
    @DeleteMapping("/api/carrito/{Id}/linea/{idArticulo}")
    public Carrito borrarLinea(@PathVariable Long Id, @PathVariable Long idArticulo) {
        return servicioCarrito.borrarLinea(Id, idArticulo);
    }
}
