package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Adds a professor to the address book.
 */
public class AddProfCommand extends Command {
    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_SUCCESS = "Professors Added!";

    public static final String MESSAGE_USAGE = AddProfCommand.COMMAND_WORD + "Adds a professor to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "{name}"
            + "Example usage: " + COMMAND_WORD + " "
            + PREFIX_NAME + "aaron";

    private NameContainsKeywordsPredicate profToAdd;

    /**
     * Constructs an AddProfCommand to add the specified professor.
     *
     * @param name The name predicate to be used for filtering professors to be added.
     */
    public AddProfCommand(NameContainsKeywordsPredicate name) {
        this.profToAdd = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredProfList(profToAdd);
        for (Person person : model.getFilteredProfList()) {
            model.addPerson(person);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
