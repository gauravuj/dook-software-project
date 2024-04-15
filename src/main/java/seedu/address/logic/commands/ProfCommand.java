package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Adds professors to the address book. Can add all professors or those matching a given name.
 */
public class ProfCommand extends Command {
    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_SUCCESS = "Professors Added!";
    public static final String MESSAGE_FAILURE = "No professors found!\nPlease double check professors name.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds professors to the address book.\n"
            + "Parameters: -a (to add all professors) or "
            + PREFIX_NAME + "NAME (to add professors by name)\n"
            + "Example: " + COMMAND_WORD + " -a\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Aaron";

    private final Predicate<Person> profPredicate;

    /**
     * Constructor for creating an {@code ProfCommand}
     * Creates a ProfCommand object that when executed, adds all professors filtered by profPredicate.
     *
     * @param profPredicate The {@code NameContainsKeywordsPredicate} used for filtering professors by name.
     */
    public ProfCommand(Predicate<Person> profPredicate) {
        requireNonNull(profPredicate);
        this.profPredicate = profPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredProfList(this.profPredicate);

        // No profs found by filter
        if (model.getFilteredProfList().isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        for (Person person : model.getFilteredProfList()) {
            try {
                model.addPerson(person);
            } catch (DuplicatePersonException e) {
                // Empty catch to prevent unexpected behaviors when adding duplicate contacts
            }
        }

        // reset user view
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfCommand)) {
            return false;
        }

        ProfCommand otherProfCommand = (ProfCommand) other;
        return profPredicate.equals(otherProfCommand.profPredicate);
    }
}
