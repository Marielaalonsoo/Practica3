package edu.comillas.icai.gitt.pat.spring.Practica5;

import edu.comillas.icai.gitt.pat.spring.Practica5.repositorios.RepoCarrito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestCarritoIntegracion {

    @Autowired MockMvc mvc;
    @Autowired RepoCarrito repoCarrito;

    @Test
    void crearCarrito_devuelve201_ySeGuarda() throws Exception {
        long before = repoCarrito.count();

        String body = """
        {
          "idCarrito": 123,
          "idUsuario": 10,
          "correo": "simple123@correo.com",
          "PrecioTotal": 0
        }
        """;

        mvc.perform(post("/api/carrito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        Assertions.assertEquals(before + 1, repoCarrito.count());
    }
}
