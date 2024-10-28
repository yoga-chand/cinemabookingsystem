package com.gic.booking.service;

import com.gic.booking.dto.BookingDetailsDto;
import com.gic.booking.dto.BookingSetupDto;
import com.gic.booking.dto.SeatsDto;

import java.util.List;

public interface BookingService {

    List<SeatsDto> allocateSeats(BookingSetupDto bookingSetupDto, int numberOfTickets, int seatsAvailable);
    BookingDetailsDto bookTickets(int existingBookingCount, List<SeatsDto> allocatedSeatsList, int numTickets, BookingSetupDto bookingSetupDto);

    void checkBookings(List<BookingDetailsDto> bookingDetailsDtoList);

}
