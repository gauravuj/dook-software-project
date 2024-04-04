package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.ProfCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new AddProfCommand object
 */
public class ProfCommandParser implements Parser<ProfCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProfCommand
     * and returns an AddProfCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ProfCommand parse(String args) throws ParseException {
        // Trim and check if the argument is exactly "-a" for adding all professors
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("-a")) {
            // Add all professors
            return new ProfCommand(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            // Process adding professors by name
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE));
            }

            String nameString = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (nameString.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = nameString.split("\\s+");
            return new ProfCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
