package com.gic.booking.dto;

public class BookingSetupDto {

    private String movieTitle;
    private int rows;
    private int seatsPerRow;
    private int seatsAvailable;
    private char[][] seatingArr;

    public BookingSetupDto(String movieTitle, int rows, int seatsPerRow, int seatsAvailable, char[][] seatingArr) {
        this.movieTitle = movieTitle;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seatsAvailable = seatsAvailable;
        this.seatingArr = seatingArr;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public char[][] getSeatingArr() {
        return seatingArr;
    }

    public void setSeatingArr(char[][] seatingArr) {
        this.seatingArr = seatingArr;
    }
}
