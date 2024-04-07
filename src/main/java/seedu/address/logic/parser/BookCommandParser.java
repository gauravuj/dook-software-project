package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Description;
import seedu.address.model.booking.EndTime;
import seedu.address.model.booking.Notes;
import seedu.address.model.booking.StartTime;

/**
 * Parses input arguments and creates a new BookCommand object
 */
public class BookCommandParser implements Parser<BookCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BookCommand
     * and returns an BookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NOTES);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DESCRIPTION, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NOTES);

        // If note field is empty
        if (argMultimap.getValue(PREFIX_NOTES).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_NOTE_MISSING));
        }

        Description description = ParserUtil.parseBookingName(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        StartTime start = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());
        EndTime end = ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_END_TIME).get());

        // Parse note only if the prefix is present
        Notes note = argMultimap.getValue(PREFIX_NOTES).isPresent()
                ? ParserUtil.parseBookingNote(argMultimap.getValue(PREFIX_NOTES).get())
                : null;

        Booking booking = new Booking(description, start, end, note);

        return new BookCommand(booking);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
