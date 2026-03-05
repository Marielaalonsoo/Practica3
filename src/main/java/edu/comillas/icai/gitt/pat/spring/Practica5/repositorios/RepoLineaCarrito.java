package edu.comillas.icai.gitt.pat.spring.Practica5.repositorios;

import edu.comillas.icai.gitt.pat.spring.Practica5.entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.Practica5.entity.LineaCarrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoLineaCarrito extends CrudRepository<LineaCarrito, Long> {

}