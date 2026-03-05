package edu.comillas.icai.gitt.pat.spring.Practica5.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LineaCarrito {
    @Id //clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se crea los valores automaticamente
    public Long idArticulo;


    //porque son fk(se comparten con otra tabla)
    //una lineaCarrito puede aparecer en muchos carritos
    @ManyToOne(optional = false) //opcional=false para indicar que el obligatorio
    @JoinColumn(name = "idCarrito", nullable = false)
    public Carrito carrito;

    @Column(nullable = false)
    public Long PrecioUnitario;

    @Column(nullable = false)
    public Long NumeroUnidades;

    @Column(nullable = true) //puedes no moterle nda porque asi lo inicializa calculandoselo a partir de los otros?????
    public Long Coste; //comprobar que nollable

    // metodo para calcular el precio total  de la linea


    public void costelinea(){
        this.Coste= this.PrecioUnitario * this.NumeroUnidades;
    }

}