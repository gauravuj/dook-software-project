package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Deletes one or all bookings from the address book.
 */
public class CancelCommand extends Command {

    public static final String COMMAND_WORD = "cancel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes bookings from the address book.\n"
            + "Parameters: -a (to delete all bookings) or {index} (must be a positive integer for a single booking)\n"
            + "Example usage: " + COMMAND_WORD + " -a (deletes all bookings)\n"
            + "Example usage: " + COMMAND_WORD + " 1 (deletes booking at index 1)";

    public static final String MESSAGE_CANCEL_ALL_SUCCESS = "Cancelled all bookings!";
    public static final String MESSAGE_CANCEL_BOOKING_SUCCESS = "Cancelled Booking: %s";

    private final Index targetIndex;
    private final boolean cancelAll;

    /**
     * Constructor for creating a {@code CancelCommand} to cancel a specific booking.
     * This constructor is used when the user wants to cancel a single booking identified by its index in
     * the list.
     *
     * @param targetIndex The {@code Index} of the booking in the list to be cancelled. Must not be null.
     */
    public CancelCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.cancelAll = false;
    }

    /**
     * Constructor for creating a {@code CancelCommand} to cancel all bookings.
     * This constructor sets the command to cancel all bookings when the {@code cancelAll} flag is true.
     *
     * @param cancelAll A boolean flag to indicate that all bookings should be cancelled. Should be true
     *                 for this constructor.
     */
    public CancelCommand(boolean cancelAll) {
        this.targetIndex = null;
        this.cancelAll = cancelAll;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (cancelAll) {
            model.cancelAllBookings();
            return new CommandResult(MESSAGE_CANCEL_ALL_SUCCESS);
        } else {
            List<Booking> lastShownList = model.getFilteredBookingList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
            }

            Booking bookingToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.cancelBooking(bookingToDelete);
            return new CommandResult(String.format(MESSAGE_CANCEL_BOOKING_SUCCESS, bookingToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CancelCommand)) {
            return false;
        }

        CancelCommand otherCancelCommand = (CancelCommand) other;
        return (targetIndex != null && targetIndex.equals(otherCancelCommand.targetIndex))
                || (cancelAll && otherCancelCommand.cancelAll);
    }
}
