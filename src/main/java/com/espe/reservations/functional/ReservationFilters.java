package com.espe.reservations.functional;

import com.espe.reservations.model.ReservationEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

//Lógica funcional
public final class ReservationFilters {

    //Predicate
    public static final Predicate<ReservationEvent> VALID_RESERVATION =
            event -> event.getPrice() > 0 && !event.getEmails().isEmpty();

    //Consumer
    public static final Consumer<ReservationEvent> PRINT_EVENT =
            event -> System.out.println("Processing reservation -> " + event);

    // Clase utilitaria
    private ReservationFilters() {
    }
}
