package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class AddProfCommand extends Command {
    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_SUCCESS = "Professor %1s Added!";

    public static final String MESSAGE_USAGE = AddProfCommand.COMMAND_WORD + "Adds a professor to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "{name}"
            + "Example usage: " + COMMAND_WORD + " "
            + PREFIX_NAME + "aaron";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    String profToAdd;

    public AddProfCommand(String name) {
        this.profToAdd = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person professor = fuzzyFind(profToAdd, model);
        if (model.hasPerson(professor)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addPerson(professor);
        return new CommandResult(String.format(MESSAGE_SUCCESS, profToAdd));
    }

    private Person fuzzyFind(String query, Model model) {

        int maxScore = Integer.MIN_VALUE;
        Person matchedPerson = null;
        for (Person person : model.getProfData().getPersonList()) {
            int score = fuzzyMatch(query, person.getName().fullName);
            if (score > maxScore) {
                maxScore = score;
                matchedPerson = person;
            }
        }
        return matchedPerson;
    }

    private static int fuzzyMatch(String s1, String s2) {
        int score = 0;
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                score++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return score;
    }
}
