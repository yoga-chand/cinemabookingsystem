package com.gic.booking.helper;

import com.gic.booking.dto.BookingSetupDto;

import java.util.Scanner;

public class BookingSystemHelper {

    public BookingSetupDto collectInputForDataSetup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title and seating map in [Title] [Row] [SeatsPerRow] format: ");
        String details = scanner.nextLine();
        String[] parts = details.split(" ");
        if (parts.length == 3) {
            String movieTitle = parts[0];
            int rows = Integer.parseInt(parts[1]);
            int seatsPerRow = Integer.parseInt(parts[2]);
            int seatsAvailable;
            if (rows > 26 || seatsPerRow > 50) {
                System.out.println("Exceeded maximum limits: 26 rows and 50 seats per rows.");
                return null;
            }
            char[][] seatingArr = new char[rows][seatsPerRow];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seatsPerRow; j++) {
                    seatingArr[i][j] = '.';
                }
            }
            seatsAvailable = rows * seatsPerRow;
            return new BookingSetupDto(movieTitle, rows, seatsPerRow, seatsAvailable, seatingArr);
        }
        return null;
    }

}
