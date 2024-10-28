package com.gic.booking.service;

import static org.junit.jupiter.api.Assertions.*;

import com.gic.booking.dto.BookingDetailsDto;
import com.gic.booking.dto.BookingSetupDto;
import com.gic.booking.dto.SeatsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class CinemaBookingServiceTest {

    private CinemaBookingService cinemaBookingService;
    private BookingSetupDto bookingSetupDto;

    @BeforeEach
    public void setUp() {
        cinemaBookingService = new CinemaBookingService();
        char[][] seatingArr = {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        };
        bookingSetupDto = new BookingSetupDto("Movie Title", 5, 10, 50, seatingArr);
    }

    @Test
    public void testAllocateSeats_SuccessfulAllocation() {
        List<SeatsDto> allocatedSeats = cinemaBookingService.allocateSeats(bookingSetupDto, 4, 50);
        assertEquals(4, allocatedSeats.size());
        for (SeatsDto seat : allocatedSeats) {
            assertTrue(seat.getRow() >= 0 && seat.getRow() < 5);
            assertTrue(seat.getCol() >= 0 && seat.getCol() < 10);
        }
    }


    @Test
    public void testBookTickets() {
        List<SeatsDto> allocatedSeats = cinemaBookingService.allocateSeats(bookingSetupDto, 2, 50);
        BookingDetailsDto bookingDetails = cinemaBookingService.bookTickets(0, allocatedSeats,2, bookingSetupDto);
        assertNotNull(bookingDetails);
        assertEquals(1, bookingDetails.getBookingId());
        assertEquals(allocatedSeats.size(), bookingDetails.getSeats().size());
    }

    @Test
    public void testCheckBookings_NoBookings() {
        List<BookingDetailsDto> bookingDetailsList = new ArrayList<>();
        cinemaBookingService.checkBookings(bookingDetailsList);
    }

    @Test
    public void testCheckBookings_WithBookings() {
        List<BookingDetailsDto> bookingDetailsList = new ArrayList<>();
        List<SeatsDto> allocatedSeats = cinemaBookingService.allocateSeats(bookingSetupDto, 3, 50);
        BookingDetailsDto bookingDetails = cinemaBookingService.bookTickets(0, allocatedSeats, 3, bookingSetupDto);
        bookingDetailsList.add(bookingDetails);

        cinemaBookingService.checkBookings(bookingDetailsList);
    }
}
