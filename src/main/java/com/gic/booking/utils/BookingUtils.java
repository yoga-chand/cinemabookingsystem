package com.gic.booking.utils;

import com.gic.booking.dto.BookingSetupDto;
import com.gic.booking.dto.SeatsDto;

import java.util.List;

public class BookingUtils {

    public static void displayMainMenu(String movieTitle, int seatsAvailable) {
        System.out.println("\nWelcome to Cinema booking system");
        System.out.println("[1] Book tickets for " + movieTitle + " (" + seatsAvailable + " seats available)");
        System.out.println("[2] Check bookings");
        System.out.println("[3] Exit");
    }

    public static void updateSeatingMap(List<SeatsDto> seatsDtoList, BookingSetupDto bookingSetupDto) {
        for (SeatsDto seatsDto : seatsDtoList) {
            bookingSetupDto.getSeatingArr()[seatsDto.getRow()][seatsDto.getCol()] = 'o'; // Mark reserved seat
            int seatsAvailable = bookingSetupDto.getSeatsAvailable() -1;
            bookingSetupDto.setSeatsAvailable(seatsAvailable);
        }
    }

    public static void displayBookedSeats(BookingSetupDto bookingSetupDto) {
        System.out.println("\n      SCREEN");
        System.out.println("——————————————");
        for (int i = 0; i <= bookingSetupDto.getSeatingArr().length - 1; i++) {
            System.out.print((char) (65 + i) + " ");
            for (char seat : bookingSetupDto.getSeatingArr()[i]) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 1; i <= bookingSetupDto.getSeatingArr()[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
