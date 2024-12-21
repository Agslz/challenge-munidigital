
# Documentación de la API Washer

## Descripción
La API Washer permite gestionar clientes, vehículos, turnos (citas) y cobros asociados a un servicio de lavado de autos. A través de esta API, es posible realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las entidades principales y manejar las relaciones entre ellas.

### Entidades principales:
1. **Cliente**:
    - Representa un cliente que puede registrar múltiples vehículos.
    - Atributos:
        - `id`: Identificador único del cliente.
        - `nombre`: Nombre completo del cliente.
        - `correoElectronico`: Dirección de correo electrónico del cliente.
        - `telefono`: Número de contacto del cliente.

2. **Vehículo**:
    - Vehículos asociados a un cliente.
    - Atributos:
        - `id`: Identificador único del vehículo.
        - `modelo`: Modelo del vehículo.
        - `matricula`: Matrícula del vehículo.
        - `tipo`: Tipo del vehículo (por ejemplo, sedán, SUV).
    - Relaciones:
        - Pertenece a un cliente (`Cliente`).
        - Puede tener múltiples turnos (`Turno`).

3. **Turno**:
    - Representa una cita o reserva para el servicio de lavado de un vehículo.
    - Atributos:
        - `id`: Identificador único del turno.
        - `fechaHora`: Fecha y hora programada para el turno.
        - `estado`: Estado del turno (por ejemplo, pendiente, completado).
        - `tipoServicio`: Descripción del tipo de servicio solicitado.
    - Relaciones:
        - Está asociado a un vehículo (`Vehículo`).
        - Puede estar asociado a un cobro (`Cobro`).

4. **Cobro**:
    - Representa un pago asociado a un turno.
    - Atributos:
        - `id`: Identificador único del cobro.
        - `monto`: Monto total del cobro.
        - `fecha`: Fecha en que se registró el cobro.
    - Relaciones:
        - Está asociado a un turno (`Turno`).

## Diagrama de Clases
El siguiente diagrama ilustra las relaciones entre las entidades principales de la API Washer:

![Diagrama de Clases](/images/UMLWasher.png)

## Endpoints

| **Método** | **Endpoint**               | **Descripción**                               | **Parámetros**                                                                                  | **Ejemplo CURL**                                                                                                                                                      |
|------------|----------------------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Cliente** |
| POST       | `/api/clientes`           | Crear un nuevo cliente                        | `nombre` (String), `correoElectronico` (String), `telefono` (String)                            | `curl -X POST http://localhost:8080/api/clientes -H 'Content-Type: application/json' -d '{"nombre":"Juan Perez","correoElectronico":"juan.perez@example.com","telefono":"123456789"}'` |
| GET        | `/api/clientes`           | Listar todos los clientes                    | -                                                                                               | `curl -X GET http://localhost:8080/api/clientes`                                                                                                                   |
| DELETE     | `/api/clientes/{id}`      | Eliminar un cliente                          | `id` (Long)                                                                                     | `curl -X DELETE http://localhost:8080/api/clientes/1`                                                                                                               |
| **Vehículo** |
| POST       | `/api/vehiculos`          | Crear un nuevo vehículo asociado a un cliente | `clienteId` (Long como query param), `modelo` (String), `matricula` (String), `tipo` (String)   | `curl -X POST "http://localhost:8080/api/vehiculos?clienteId=1" -H 'Content-Type: application/json' -d '{"modelo":"Toyota Corolla","matricula":"ABC123","tipo":"Sedán"}'` |
| GET        | `/api/vehiculos`          | Listar todos los vehículos                   | -                                                                                               | `curl -X GET http://localhost:8080/api/vehiculos`                                                                                                                  |
| DELETE     | `/api/vehiculos/{id}`     | Eliminar un vehículo                         | `id` (Long)                                                                                     | `curl -X DELETE http://localhost:8080/api/vehiculos/1`                                                                                                              |
| **Turno** |
| POST       | `/api/turnos`             | Crear un turno para un vehículo              | `fechaHora` (Date), `estado` (String), `tipoServicio` (String), `vehiculo.id` (Long)            | `curl -X POST http://localhost:8080/api/turnos -H 'Content-Type: application/json' -d '{"fechaHora":"2024-12-25T15:00:00","estado":"pendiente","tipoServicio":"lavado completo","vehiculo":{"id":1}}'` |
| PUT        | `/api/turnos/{id}/estado` | Actualizar el estado de un turno             | `estado` (String como query param)                                                             | `curl -X PUT "http://localhost:8080/api/turnos/1/estado?estado=completado"`                                                                                          |
| GET        | `/api/turnos`             | Listar todos los turnos                      | -                                                                                               | `curl -X GET http://localhost:8080/api/turnos`                                                                                                                     |
| DELETE     | `/api/turnos/{id}`        | Eliminar un turno                            | `id` (Long)                                                                                     | `curl -X DELETE http://localhost:8080/api/turnos/1`                                                                                                                 |
| **Cobro** |
| POST       | `/api/cobros`             | Crear un cobro asociado a un turno           | `monto` (Double), `fecha` (Date), `turno.id` (Long)                                            | `curl -X POST http://localhost:8080/api/cobros -H 'Content-Type: application/json' -d '{"monto":200.00,"fecha":"2024-12-25T17:00:00","turno":{"id":1}}'`            |
| GET        | `/api/cobros`             | Listar todos los cobros                      | -                                                                                               | `curl -X GET http://localhost:8080/api/cobros`                                                                                                                     |
| DELETE     | `/api/cobros/{id}`        | Eliminar un cobro                            | `id` (Long)                                                                                     | `curl -X DELETE http://localhost:8080/api/cobros/1`                                                                                                                 |
