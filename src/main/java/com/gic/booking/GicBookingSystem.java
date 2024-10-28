package com.gic.booking;

import com.gic.booking.helper.BookingSystemHelper;
import com.gic.booking.dto.BookingDetailsDto;
import com.gic.booking.dto.BookingSetupDto;
import com.gic.booking.dto.SeatsDto;
import com.gic.booking.service.BookingService;
import com.gic.booking.service.CinemaBookingService;
import com.gic.booking.utils.BookingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GicBookingSystem {

    List<BookingDetailsDto> bookingDetailsDtoList;

    public GicBookingSystem() {
        this.bookingDetailsDtoList = new ArrayList<>();
    }

    Integer checkAndBookTickets(int seatsAvailable, BookingService bookingService, BookingSetupDto bookingSetupDto) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter number of tickets to book or enter blank to go back to main menu: ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                return null;
            }

            int numTickets = Integer.parseInt(input);
            if (numTickets > seatsAvailable) {
                System.out.println("Not enough seats available. Try again.");
                continue;
            }
            List<SeatsDto> selectedSeatsList = bookingService.allocateSeats(bookingSetupDto, numTickets, bookingSetupDto.getSeatsAvailable());
            if (!selectedSeatsList.isEmpty()) {
                BookingDetailsDto bookingDetailsDto = bookingService.bookTickets(bookingDetailsDtoList.size(), selectedSeatsList, bookingSetupDto);
                bookingDetailsDtoList.add(bookingDetailsDto);
            } else {
                System.out.println("Unable to allocate seats. Try again.");
            }
        }
    }


    void executeBookingSystem() {
        BookingSystemHelper bookingSystemHelper = new BookingSystemHelper();
        BookingSetupDto bookingSetupDto = bookingSystemHelper.collectInputForDataSetup();
        if (bookingSetupDto != null) {
            while (true) {
                BookingUtils.displayMainMenu(bookingSetupDto.getMovieTitle(), bookingSetupDto.getSeatsAvailable());
                Scanner scanner = new Scanner(System.in);
                String selectedOption = scanner.nextLine();
                BookingService bookingService = new CinemaBookingService();
                switch (selectedOption) {
                    case "1":
                        checkAndBookTickets(bookingSetupDto.getSeatsAvailable(), bookingService, bookingSetupDto);
                        break;
                    case "2":
                        bookingService.checkBookings(bookingDetailsDtoList);
                        break;
                    case "3":
                        System.out.println("Exiting the system.");
                        return;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                }
            }

        }
    }


    public static void main(String[] args) {
        GicBookingSystem bookingSystem = new GicBookingSystem();
        bookingSystem.executeBookingSystem();
    }
}
