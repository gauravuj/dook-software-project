package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all bookings in the application to the user.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Listed all bookings";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(unused -> true);
        return new CommandResult(MESSAGE_SUCCESS + ". Total: " + model.getFilteredBookingList().size());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ViewCommand;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
