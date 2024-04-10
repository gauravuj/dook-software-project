package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    public static final Booking CS1231_CONSULT = new BookingBuilder()
            .withDescription("CS1231 Consult")
            .withStartTime("2022-11-30 11:00")
            .withEndTime("2022-11-30 12:00")
            .build();

    public static final Booking CAREER_ADVISORY = new BookingBuilder()
            .withDescription("Career advisory consult")
            .withStartTime("2024-01-12 11:00")
            .withEndTime("2024-01-12 12:00")
            .build();

    public static final Booking CS2103T_CONSULT = new BookingBuilder()
            .withDescription("CS2103T consult")
            .withStartTime("2024-03-09 14:00")
            .withEndTime("2024-03-09 16:00")
            .build();

    public static final Booking CS2101_CONSULT = new BookingBuilder()
            .withDescription("CS2101 consult")
            .withStartTime("2024-03-24 14:00")
            .withEndTime("2024-03-24 16:00")
            .build();

    public static final Booking CS2109S_CONSULT = new BookingBuilder()
            .withDescription("CS2109 consult")
            .withStartTime("2024-05-22 11:00")
            .withEndTime("2024-05-22 13:00")
            .build();

    public static final Booking GENERIC_BOOKING = new BookingBuilder()
            .build();

    // Keywords
    public static final String KEYWORD_MATCHING_BOOK = "Book";

    /**
     * Returns an {@code AddressBook} with all the typical booking.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Booking booking : getTypicalBooking()) {
            ab.addBooking(booking);
        }
        return ab;
    }

    public static List<Booking> getTypicalBooking() {
        return new ArrayList<>(Arrays.asList(
                CS1231_CONSULT, CS2101_CONSULT, CS2103T_CONSULT, CS2109S_CONSULT));
    }
}
