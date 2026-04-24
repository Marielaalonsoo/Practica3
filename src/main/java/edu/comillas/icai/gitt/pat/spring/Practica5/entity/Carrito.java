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
    public String idUsuario;

    @Column(nullable = false, unique = true) //no puede haber varios cariitos con el mismo email
    public String correo;

    @Column(nullable = false) //puede haber varios carritos con el mismo precio total(coincidencia)
    public double precioTotal = 0.0;

    // me creo una columna adicional que es la lineadecarritos(productos que pertenecen al carrito)
    //un mismo carrito va poder tener varias lineas de carrito

    @OneToMany
    (mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineas = new ArrayList<>(); //me creo una clase de linea de carritos


    public  void nuevaLinea(LineaCarrito linea){
        lineas.add(linea) ;
        linea.carrito = this;
        CalcularPrecio();
    }

    public void CalcularPrecio(){
        double precio= 0.0 ;
        for (LineaCarrito linea : lineas) {
            precio += linea.costelinea();
        }
        this.precioTotal= precio;
    }

    public List<LineaCarrito> getLineas() {
        return lineas;
    }

    public void borrarLinea(LineaCarrito linea){
        lineas.remove(linea);//borro linea
        CalcularPrecio();
    }
}