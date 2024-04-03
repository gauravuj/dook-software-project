package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdateBookingDescriptor;
import seedu.address.testutil.UpdateBookingDescriptorBuilder;

public class UpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_BOOKING_DESCRIPTION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateCommand.MESSAGE_NOT_UPDATED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + BOOKING_DESC_TEST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + BOOKING_DESC_TEST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BOOKING;
        String userInput = targetIndex.getOneBased() + BOOKING_DESC_TEST + START_TIME_DESC_TEST
                + END_TIME_DESC_TEST + NOTE_DESC_TEST;

        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder()
                .withDescription(VALID_BOOKING_DESCRIPTION).withEndTime(VALID_END_TIME)
                .withNotes(VALID_NOTE)
                .withStartTime(VALID_START_TIME).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BOOKING;
        String userInput = targetIndex.getOneBased() + END_TIME_DESC_TEST + NOTE_DESC_TEST;

        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder().withNotes(VALID_NOTE)
                .withEndTime(VALID_END_TIME).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_FIRST_BOOKING;
        String userInput = targetIndex.getOneBased() + BOOKING_DESC_TEST;
        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder()
                .withDescription(VALID_BOOKING_DESCRIPTION).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_TEST;
        descriptor = new UpdateBookingDescriptorBuilder().withStartTime(VALID_START_TIME).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_TEST;
        descriptor = new UpdateBookingDescriptorBuilder().withEndTime(VALID_END_TIME).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // note
        userInput = targetIndex.getOneBased() + NOTE_DESC_TEST;
        descriptor = new UpdateBookingDescriptorBuilder().withNotes(VALID_NOTE).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_BOOKING;
        // mulltiple valid fields repeated
        String userInput = targetIndex.getOneBased() + NOTE_DESC_TEST + BOOKING_DESC_TEST + START_TIME_DESC_TEST
                + END_TIME_DESC_TEST + BOOKING_DESC_TEST + NOTE_DESC_TEST + START_TIME_DESC_TEST + BOOKING_DESC_TEST;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION, PREFIX_START_TIME, PREFIX_NOTES));
    }

}
