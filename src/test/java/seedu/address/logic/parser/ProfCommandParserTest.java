package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProfCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class ProfCommandParserTest {
    private ProfCommandParser parser = new ProfCommandParser();

    @Test
    public void parse_validInput_success() {
        // add all prof flag (-a): spaces dont matter
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + "     -a   ",
                new ProfCommand(PREDICATE_SHOW_ALL_PERSONS));


        // add specific prof flag (-n)
        List<String> keyWords = new ArrayList<>();
        keyWords.add("seth");
        keyWords.add("tan");

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keyWords);
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + " " + PREFIX_NAME + "seth tan",
                new ProfCommand(predicate));
    }

    @Test
    public void parse_bothFlag_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE);
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + "   -a    " + PREFIX_NAME + "seth tan",
                expectedMessage);
    }
}
