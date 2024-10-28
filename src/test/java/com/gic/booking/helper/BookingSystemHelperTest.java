package com.gic.booking.helper;

import com.gic.booking.dto.BookingSetupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemHelperTest {

    private BookingSystemHelper bookingSystemHelper;

    @BeforeEach
    void setUp() {
        bookingSystemHelper = new BookingSystemHelper();
    }

    @Test
    void testCollectInputForDataSetup_ValidInput() {
        String input = "Avengers 5 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BookingSetupDto result = bookingSystemHelper.collectInputForDataSetup();

        assertNotNull(result);
        assertEquals("Avengers", result.getMovieTitle());
        assertEquals(5, result.getRows());
        assertEquals(10, result.getSeatsPerRow());
        assertEquals(50, result.getSeatsAvailable());
        assertEquals(5, result.getSeatingArr().length);
        assertEquals(10, result.getSeatingArr()[0].length);
        assertEquals('.', result.getSeatingArr()[0][0]);
    }

    @Test
    void testCollectInputForDataSetup_ExceededRowLimit() {
        String input = "Movie 27 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BookingSetupDto result = bookingSystemHelper.collectInputForDataSetup();

        assertNull(result);
    }

    @Test
    void testCollectInputForDataSetup_ExceededSeatPerRowLimit() {
        String input = "Movie 10 51\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BookingSetupDto result = bookingSystemHelper.collectInputForDataSetup();

        assertNull(result);
    }

    @Test
    void testCollectInputForDataSetup_InvalidInputFormat() {
        String input = "Movie 5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BookingSetupDto result = bookingSystemHelper.collectInputForDataSetup();

        assertNull(result);
    }
}
