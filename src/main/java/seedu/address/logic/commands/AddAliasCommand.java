package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command to add an alias for a specified term.
 * An alias is a shorthand representation for a longer term.
 */
public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_SUCCESS = "New Alias added: %1$s";

    private final String alias;
    private final String toReplace;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAliasCommand(String alias, String toReplace) {
        requireNonNull(alias);
        requireNonNull(toReplace);
        this.alias = alias;
        this.toReplace = toReplace;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAlias(alias, toReplace);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAliasCommand)) {
            return false;
        }

        AddAliasCommand otherAddAliasCommand = (AddAliasCommand) other;
        return alias.equals(otherAddAliasCommand.alias)
                && toReplace.equals(otherAddAliasCommand.toReplace);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("alias", alias)
                .add("toReplace", toReplace)
                .toString();
    }
}
