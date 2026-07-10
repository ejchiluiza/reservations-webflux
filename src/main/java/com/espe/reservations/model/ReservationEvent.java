package com.espe.reservations.model;

import java.util.ArrayList;
import java.util.List;

//reserva de vuelo
public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        //Copia defensiva
        this.emails = new ArrayList<>(emails == null ? List.of() : emails);
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }


    //Copia defensiva
    public List<String> getEmails() {
        return new ArrayList<>(emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}
