package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.*;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

/**
 * Edits the details of an existing person in the address book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified "
            + "by the index number used in the displayed booking list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: {index} (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "{description}] "
            + "[" + PREFIX_START_TIME + "{start}] "
            + "[" + PREFIX_END_TIME + "{end}] "
            + "[" + PREFIX_NOTES + "{note}]...\n"
            + "Example usage: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "cs2103t consult "
            + PREFIX_START_TIME + "2024-12-13 13:00";

    public static final String MESSAGE_UPDATE_BOOKING_SUCCESS = "UPDATES BOOKING: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the address book.";


    private final Index index;
    private final UpdateBookingDescriptor updateBookingDescriptor;

    /**
     * @param index of the booking in the filtered booking list to update
     * @param updateBookingDescriptor details to update the booking with
     */
    public UpdateCommand(Index index, UpdateBookingDescriptor updateBookingDescriptor) {
        requireNonNull(index);
        requireNonNull(updateBookingDescriptor);

        this.index = index;
        this.updateBookingDescriptor = new UpdateBookingDescriptor(updateBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToUpdate = lastShownList.get(index.getZeroBased());
        Booking updatedBooking = createUpdatedBooking(bookingToUpdate, updateBookingDescriptor);

        if (!bookingToUpdate.equals(updatedBooking) && model.hasBooking(updatedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.setBooking(bookingToUpdate, updatedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(String.format(MESSAGE_UPDATE_BOOKING_SUCCESS, Messages.format(updatedBooking)));
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToUpdate}
     * updated with {@code updateBookingDescriptor}.
     */
    private static Booking createUpdatedBooking(Booking bookingToUpdate, UpdateBookingDescriptor updateBookingDescriptor) {
        assert bookingToUpdate != null;

        Description updatedDescription = updateBookingDescriptor.getDescription().orElse(bookingToUpdate.getDescription());
        StartTime updatedStartTime = updateBookingDescriptor.getStart().orElse(bookingToUpdate.getStart());
        EndTime updatedEndTime = updateBookingDescriptor.getEnd().orElse(bookingToUpdate.getEnd());
        Notes updatedNotes = updateBookingDescriptor.getNote().orElse(bookingToUpdate.getNotes());

        return new Booking(updatedDescription, updatedStartTime, updatedEndTime, updatedNotes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        UpdateCommand otherUpdateCommand = (UpdateCommand) other;
        return index.equals(otherUpdateCommand.index)
                && updateBookingDescriptor.equals(otherUpdateCommand.updateBookingDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("updateBookingDescriptor", updateBookingDescriptor)
                .toString();
    }

    /**
     * Stores the details to update the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class UpdateBookingDescriptor {
        private Description description;
        private StartTime start;
        private EndTime end;
        private Notes note;

        public UpdateBookingDescriptor() {}

        /**
         * Copy constructor.
         */
        public UpdateBookingDescriptor(UpdateBookingDescriptor toCopy) {
            setDescription(toCopy.description);
            setStart(toCopy.start);
            setEnd(toCopy.end);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, start, end, note);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStart(StartTime start) {
            this.start = start;
        }

        public Optional<StartTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setEnd(EndTime end) {
            this.end = end;
        }

        public Optional<EndTime> getEnd() {
            return Optional.ofNullable(end);
        }

        public void setNote(Notes note) {
            this.note = note;
        }

        public Optional<Notes> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateBookingDescriptor)) {
                return false;
            }

            UpdateBookingDescriptor otherUpdateBookingDescriptor = (UpdateBookingDescriptor) other;
            return Objects.equals(description, otherUpdateBookingDescriptor.description)
                    && Objects.equals(start, otherUpdateBookingDescriptor.start)
                    && Objects.equals(end, otherUpdateBookingDescriptor.end)
                    && Objects.equals(note, otherUpdateBookingDescriptor.note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("description", description)
                    .add("start time", start)
                    .add("end time", end)
                    .add("notes", note)
                    .toString();
        }
    }
}

