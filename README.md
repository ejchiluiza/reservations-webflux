# Reservations WebFlux

Tarea de Programacion Avanzada - ESPE.
Edison Chiluiza

Modulo reactivo que procesa eventos de reserva con Spring WebFlux y Project Reactor.
Expone el endpoint GET /api/reservations/stream que retorna un Flux de reservas.

Para ejecutar: mvnw spring-boot:run
Para probar: curl -N http://localhost:8080/api/reservations/stream