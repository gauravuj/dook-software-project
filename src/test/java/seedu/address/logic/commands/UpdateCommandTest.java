package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalBookings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.UpdateCommand.UpdateBookingDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProfData;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.UpdateBookingDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateCommand.
 */
public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Booking updatedBooking = new BookingBuilder().build();
        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder(updatedBooking).build();
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_BOOKING, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_BOOKING_SUCCESS, Messages.format(updatedBooking));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ProfData(model.getProfData()),
                new UserPrefs());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), updatedBooking);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBooking = Index.fromOneBased(model.getFilteredBookingList().size());
        Booking lastBooking = model.getFilteredBookingList().get(indexLastBooking.getZeroBased());

        BookingBuilder bookingInList = new BookingBuilder(lastBooking);
        Booking updatedBooking = bookingInList.withDescription(VALID_BOOKING_DESCRIPTION).withEndTime(VALID_END_TIME)
                .withNote(VALID_NOTE).build();

        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder().withDescription(VALID_BOOKING_DESCRIPTION)
                .withEndTime(VALID_END_TIME)
                .withNotes(VALID_NOTE).build();
        UpdateCommand updateCommand = new UpdateCommand(indexLastBooking, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_BOOKING_SUCCESS, Messages.format(updatedBooking));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ProfData(model.getProfData()),
                new UserPrefs());
        expectedModel.setBooking(lastBooking, updatedBooking);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_BOOKING, new UpdateBookingDescriptor());
        Booking editedPerson = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_BOOKING_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ProfData(model.getProfData()),
                new UserPrefs());

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookingAtIndex(model, INDEX_FIRST_PERSON);

        Booking bookingInFilteredList = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        Booking updatedBooking =
                new BookingBuilder(bookingInFilteredList).withDescription(VALID_BOOKING_DESCRIPTION).build();
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PERSON,
                new UpdateBookingDescriptorBuilder().withDescription(VALID_BOOKING_DESCRIPTION).build());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_BOOKING_SUCCESS,
                Messages.format(updatedBooking));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ProfData(model.getProfData()),
                new UserPrefs());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), updatedBooking);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        UpdateBookingDescriptor descriptor = new UpdateBookingDescriptorBuilder().withDescription(VALID_BOOKING_DESCRIPTION).build();
        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBookingIndexFilteredList_failure() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);
        Index outOfBoundIndex = INDEX_SECOND_BOOKING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBookingList().size());

        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex,
                new UpdateBookingDescriptorBuilder().withDescription(VALID_BOOKING_DESCRIPTION).build());

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateCommand standardCommand = new UpdateCommand(INDEX_FIRST_BOOKING, DESC_STD);

        // same values -> returns true
        UpdateBookingDescriptor copyDescriptor = new UpdateBookingDescriptor(DESC_STD);
        UpdateCommand commandWithSameValues = new UpdateCommand(INDEX_FIRST_BOOKING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_SECOND_BOOKING, DESC_STD)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_BOOKING, DESC_DIFF)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        UpdateBookingDescriptor updateBookingDescriptor = new UpdateBookingDescriptor();
        UpdateCommand updateCommand = new UpdateCommand(index, updateBookingDescriptor);
        String expected = UpdateCommand.class.getCanonicalName() + "{index=" + index + ", updateBookingDescriptor="
                + updateBookingDescriptor + "}";
        assertEquals(expected, updateCommand.toString());
    }

}
