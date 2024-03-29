package seedu.address.testutil;

import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Description;
import seedu.address.model.booking.EndTime;
import seedu.address.model.booking.Notes;
import seedu.address.model.booking.StartTime;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final String DEFAULT_DESCRIPTION = "Default booking description";
    public static final String DEFAULT_START_TIME = "2023-12-31 19:00";
    public static final String DEFAULT_END_TIME = "2023-12-31 21:00";
    public static final String DEFAULT_NOTE = "Hello World!";

    private Description description;
    private StartTime start;
    private EndTime end;
    private Notes note;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        start = new StartTime(DEFAULT_START_TIME);
        end = new EndTime(DEFAULT_END_TIME);
        this.note = new Notes(DEFAULT_NOTE);
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        description = bookingToCopy.getDescription();
        start = bookingToCopy.getStart();
        end = bookingToCopy.getEnd();
        this.note = bookingToCopy.getNotes();
    }

    /**
     * Returns the parameters required to build the booking
     */
    public String getBookingString() {
        StringBuilder builder = new StringBuilder();

        builder.append(" ")
                .append(CliSyntax.PREFIX_DESCRIPTION)
                .append(this.description)
                .append(" ")
                .append(CliSyntax.PREFIX_START_TIME)
                .append(this.start)
                .append(" ")
                .append(CliSyntax.PREFIX_END_TIME)
                .append(this.end);

        if (this.note != null) {
            return builder.append(" ").append(CliSyntax.PREFIX_NOTES).append(this.note).toString();
        } else {
            return builder.toString();
        }
    }

    /**
     * Sets the {@code Description} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Booking} that we are building.
     */
    public BookingBuilder withStartTime(String start) {
        this.start = new StartTime(start);
        return this;
    }

    /**
     * Sets the {@code end} of the {@code Booking} that we are building.
     */
    public BookingBuilder withEndTime(String end) {
        this.end = new EndTime(end);
        return this;
    }

    /**
     * Sets the {@code note} of the {@code Booking} that we are building.
     */
    public BookingBuilder withNote(String note) {
        this.note = new Notes(note);
        return this;
    }

    /**
     * Builds the {@code Booking} with the relevant information.
     */
    public Booking build() {
        return new Booking(description, start, end, note);
    }
}
