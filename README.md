# Practica 3 

Esta práctica consiste en desarrollar una API REST para gestionar un carrito de compra utilizando Spring Boot y JPA.

El sistema permite:

- Crear un carrito
- Consultar un carrito
- Borrar un carrito
- Añadir productos (líneas de carrito)
- Eliminar productos del carrito

Los datos se almacenan en una base de datos mediante Spring Data JPA.

## Tabla de EndPoints:

| Método | Endpoint                               | Descripción                           | Body (JSON)    | Respuesta esperada             |
| ------ | -------------------------------------- | ------------------------------------- | -------------- | ------------------------------ |
| POST   | `/api/carrito`                         | Crea un carrito                       | `Carrito`      | `201 CREATED` + carrito        |
| GET    | `/api/carrito/{Id}`                    | Devuelve un carrito por id            | —              | `200 OK` + carrito             |
| DELETE | `/api/carrito/{Id}`                    | Borra un carrito por id               | —              | `200 OK` / `204 NO CONTENT`    |
| POST   | `/api/carrito/{Id}/linea`              | Añade una línea (producto) al carrito | `LineaCarrito` | `200 OK` + carrito actualizado |
| DELETE | `/api/carrito/{Id}/linea/{idArticulo}` | Borra una línea concreta del carrito  | —              | `200 OK` + carrito actualizado |


Carrito :
{
"idCarrito": 1,
"idUsuario": 10,
"correo": "usuario@email.com",
"PrecioTotal": 0
}
LineaCarrito :
{
"idArticulo": 5,
"PrecioUnitario": 10,
"NumeroUnidades": 2,
"Coste": 20
}

## Test

Se ha añadido un test de integración simple para comprobar que la creación de carritos funciona correctamente.

El test realiza:

1. Enviar un POST /api/carrito
2. Comprobar que devuelve 201 CREATED
3. Verificar que el carrito se guarda en el repositorio.
