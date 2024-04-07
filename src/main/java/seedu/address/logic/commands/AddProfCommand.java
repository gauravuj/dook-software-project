package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Adds professors to the address book. Can add all professors or those matching a given name.
 */
public class AddProfCommand extends Command {
    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_SUCCESS = "Professors Added!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds professors to the address book.\n"
            + "Parameters: -a (to add all professors) or "
            + PREFIX_NAME + "NAME (to add professors by name)\n"
            + "Example: " + COMMAND_WORD + " -a\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Aaron";

    private final boolean addAll;
    private final NameContainsKeywordsPredicate profPredicate;

    /**
     * Constructor for creating an {@code AddProfCommand} to add all professors.
     * This constructor sets the command to add all professors without filtering by name.
     */
    public AddProfCommand() {
        this.addAll = true;
        this.profPredicate = null;
    }

    /**
     * Constructor for creating an {@code AddProfCommand} with a specific name filter.
     * This constructor sets the command to add professors filtered by the specified name predicate.
     *
     * @param profPredicate The {@code NameContainsKeywordsPredicate} used for filtering professors by name.
     */
    public AddProfCommand(NameContainsKeywordsPredicate profPredicate) {
        this.profPredicate = profPredicate;
        this.addAll = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (addAll) {
            model.updateFilteredProfList(PREDICATE_SHOW_ALL_PERSONS);
        } else {
            requireNonNull(profPredicate);
            model.updateFilteredProfList(profPredicate);
        }
        for (Person person : model.getFilteredProfList()) {
            model.addPerson(person);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
