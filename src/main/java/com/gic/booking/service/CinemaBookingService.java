package com.gic.booking.service;

import com.gic.booking.dto.BookingDetailsDto;
import com.gic.booking.dto.BookingSetupDto;
import com.gic.booking.dto.SeatsDto;
import com.gic.booking.utils.BookingUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CinemaBookingService implements BookingService {

    @Override
    public List<SeatsDto> allocateSeats(BookingSetupDto bookingSetupDto, int numberOfTickets, int seatsAvailable) {
            int rows = bookingSetupDto.getSeatingArr().length;
            int seatsPerRow = bookingSetupDto.getSeatingArr()[0].length;
            List<SeatsDto> selectedSeats = new ArrayList<>();

            for (int row = rows - 1; row >= 0; row--) {
                int middleCol = seatsPerRow / 2;
                int leftCol = middleCol - 1;
                int rightCol = middleCol;

                while ((leftCol >= 0 || rightCol < seatsPerRow) && selectedSeats.size() < numberOfTickets) {
                    if (rightCol < seatsPerRow && bookingSetupDto.getSeatingArr()[row][rightCol] == '.') {
                        selectedSeats.add(new SeatsDto(row, rightCol));
                    }
                    if (leftCol >= 0 && bookingSetupDto.getSeatingArr()[row][leftCol] == '.') {
                        selectedSeats.add(new SeatsDto(row, leftCol));
                    }
                    leftCol--;
                    rightCol++;
                }

                if (selectedSeats.size() >= numberOfTickets) {
                    return selectedSeats;
                }
            }
            return Collections.emptyList();
        }

    @Override
    public BookingDetailsDto bookTickets(int existingBookingCount, List<SeatsDto> allocatedSeatsList, int numTickets, BookingSetupDto bookingSetupDto) {
        existingBookingCount++;
        BookingDetailsDto bookingDetailsDto = new BookingDetailsDto(existingBookingCount, allocatedSeatsList);
        BookingUtils.updateSeatingMap(allocatedSeatsList, bookingSetupDto);
        System.out.println("Successfully reserved " + numTickets + " " + bookingSetupDto.getMovieTitle() + " tickets.");
        System.out.println("Booking id: " + existingBookingCount);
        BookingUtils.displayBookedSeats(bookingSetupDto);
        return bookingDetailsDto;
    }

    @Override
    public void checkBookings(List<BookingDetailsDto> bookingDetailsDtoList) {
        if (bookingDetailsDtoList.isEmpty()) {
            System.out.println("No bookings made yet.");
            return;
        }
        for (BookingDetailsDto bookingDetailsDto : bookingDetailsDtoList) {
            System.out.print("Booking ID: " + bookingDetailsDto.getBookingId() + ", Seats: ");
            for (SeatsDto seat : bookingDetailsDto.getSeats()) {
                System.out.print("(" + (char) (65 + seat.getRow()) + "," + (seat.getCol() + 1) + ") ");
            }
            System.out.println();
        }

    }
}
