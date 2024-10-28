package com.gic.booking.dto;

import java.util.List;

public class BookingDetailsDto {

    private String movieTitle;

    private int bookingId;
    private List<SeatsDto> seats;

    public BookingDetailsDto(int bookingId, List<SeatsDto> seats) {
        this.bookingId = bookingId;
        this.seats = seats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public List<SeatsDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatsDto> seats) {
        this.seats = seats;
    }
}
