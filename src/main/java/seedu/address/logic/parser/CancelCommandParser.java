package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CancelCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CancelCommand object
 */
public class CancelCommandParser implements Parser<CancelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CancelCommand
     * and returns a CancelCommand object for execution.
     * If the argument is "-a", it creates a CancelCommand to cancel all bookings.
     * Otherwise, it attempts to parse the argument as an index for a single booking cancellation.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CancelCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.equals("-a")) {
            return new CancelCommand(true); // Cancel all bookings
        } else {
            try {
                Index index = ParserUtil.parseIndex(trimmedArgs);
                return new CancelCommand(index); // Cancel a specific booking by index
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE), pe);
            }
        }
    }
}
