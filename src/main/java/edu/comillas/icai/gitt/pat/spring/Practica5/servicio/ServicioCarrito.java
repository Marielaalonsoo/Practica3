package edu.comillas.icai.gitt.pat.spring.Practica5.servicio;


import edu.comillas.icai.gitt.pat.spring.Practica5.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.entity.LineaCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.repositorios.RepoCarrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.repositorios.RepoLineaCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service

public class ServicioCarrito {

    @Autowired
    RepoLineaCarrito repoLineaCarrito;
    @Autowired
    RepoCarrito repoCarrito;

    //crearme un carito
    @Transactional
    public Carrito crea( Carrito Nuevocarrito){
        if (repoCarrito.findByIdCarrito(Nuevocarrito.idCarrito)!=null){ //si busco el nombre del carrito nuevo y ya existe en el repositorio entonces es que voy hacer un carrito repetido
            throw new ResponseStatusException(HttpStatus.CONFLICT,"carrito ya existente");
        }
        return repoCarrito.save(Nuevocarrito); //me creo un carrito nuevo y lo guardo

    }

    public Carrito lee( Long idCarrito){



        return repoCarrito.findByIdCarrito(idCarrito);

    }

    public void borra(Carrito carrito) {

        repoCarrito.delete(carrito);
    }

    //lineas

    //añadir lineas al carrito(añador producto al carrito)

    public Carrito crearlinea( LineaCarrito linea , Long idCarrito){

        //encuentro el carrito al que le quiero guardar la linea
        Carrito carrito = repoCarrito.findByIdCarrito(idCarrito);

        //llamo a una funcion que me alada los datos a la lista
        carrito.nuevaLinea(linea);

        return repoCarrito.save(carrito);
    }

    //borrar linea

    @Transactional
    public Carrito borrarLinea(Long idCarrito, Long idArticulo) {

        Carrito carrito = repoCarrito.findByIdCarrito(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }

        LineaCarrito lineaAEliminar = null;
        for (LineaCarrito l : carrito.getLineas()) {
            if (l.idArticulo != null && l.idArticulo.equals(idArticulo)) {
                lineaAEliminar = l;
                break;
            }
        }

        if (lineaAEliminar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada");
        }

        carrito.borrarLinea(lineaAEliminar); // orphanRemoval=true => se borra de BD
        carrito.CalcularPrecio();

        return repoCarrito.save(carrito);
    }


}
