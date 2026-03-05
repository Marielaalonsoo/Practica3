package edu.comillas.icai.gitt.pat.spring.Practica5.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrito {
    @Id //clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se crea los valores automaticamente
    public Long idCarrito;

    @Column(nullable = false, unique = true)    //no puede ser null y no puede haber dos carritos con el mismo iduser
    public Long idUsuario;

    @Column(nullable = false, unique = true) //no puede haber varios cariitos con el mismo email
    public String correo;

    @Column(nullable = false) //puede haber varios carritos con el mismo precio total(coincidencia)
    public Long PrecioTotal = 0L;

    // me creo una columna adicional que es la lineadecarritos(productos que pertenecen al carrito)
    //un mismo carrito va poder tener varias lineas de carrito

    @OneToMany
    (mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineas = new ArrayList<>(); //me creo una clase de linea de carritos


    public  void nuevaLinea(LineaCarrito linea){
        lineas.add(linea) ;
        linea.carrito = this;
    }

    public void CalcularPrecio(){
        Long precio= 0L ;
        for (LineaCarrito linea : lineas) {
            precio += linea.Coste;
        }
        this.PrecioTotal= precio;
    }

    public List<LineaCarrito> getLineas() {
        return lineas;
    }

    public void borrarLinea(LineaCarrito linea){
        lineas.remove(linea);//borro linea
        linea.carrito = null; //la pongo a null
    }
}