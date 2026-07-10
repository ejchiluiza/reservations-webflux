package com.espe.reservations.controller;

import com.espe.reservations.functional.ReservationFilters;
import com.espe.reservations.model.ReservationEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

//Flujo reactivo

@RestController
public class ReservationController {

    @GetMapping(value = "/api/reservations/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReservationEvent> streamReservations() {

        // Reserva genérica
        ReservationEvent defaultReservation = new ReservationEvent(
                UUID.randomUUID().toString(),
                "No valid reservations",
                0.0,
                List.of("noreply@espe.edu.ec"));

        //5 reservas en memoria

        return Flux.just(
                        new ReservationEvent(UUID.randomUUID().toString(), "Edison Chiluiza",   150.0, List.of("ejchiluiza@gmail.com")),
                        new ReservationEvent(UUID.randomUUID().toString(), "Arlette Chiluiza",   200.0, List.of("arlette@gmail.com")),
                        new ReservationEvent(UUID.randomUUID().toString(), "Nathaly Moncayo",  320.5, List.of("nmoncayo@gmail.com", "nmoncayo@espe.edu.ec")),
                        new ReservationEvent(UUID.randomUUID().toString(), "Karina Ruiz",    0.0, List.of("kruiz@gmail.com")), // inválida: precio = 0
                        new ReservationEvent(UUID.randomUUID().toString(), "Diana Pazmiño",   90.0, List.of())                   // inválida: emails vacío
                )
                .filter(ReservationFilters.VALID_RESERVATION)
                .doOnNext(ReservationFilters.PRINT_EVENT)
                .defaultIfEmpty(defaultReservation);
    }
}
