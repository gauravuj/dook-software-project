package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation will result in duplicate Bookings (Persons are considered duplicates if they have the same
 * description, start, end and notes).
 */
public class DuplicateBookingException extends RuntimeException {
    public DuplicateBookingException() {
        super("Operation would result in duplicate Bookings");
    }
}
