package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateCommand.UpdateBookingDescriptor;
import seedu.address.model.booking.*;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building UpdateBookingDescriptor objects.
 */
public class UpdateBookingDescriptorBuilder {

    private UpdateBookingDescriptor descriptor;

    public UpdateBookingDescriptorBuilder() {
        descriptor = new UpdateBookingDescriptor();
    }

    public UpdateBookingDescriptorBuilder(UpdateBookingDescriptor descriptor) {
        this.descriptor = new UpdateBookingDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateBookingDescriptor} with fields containing {@code booking}'s details
     */
    public UpdateBookingDescriptorBuilder(Booking booking) {
        descriptor = new UpdateBookingDescriptor();
        descriptor.setDescription(booking.getDescription());
        descriptor.setStart(booking.getStart());
        descriptor.setEnd(booking.getEnd());
        descriptor.setNote(booking.getNotes());
    }

    /**
     * Sets the {@code Description} of the {@code UpdateBookingDescriptor} that we are building.
     */
    public UpdateBookingDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code UpdateBookingDescriptor} that we are building.
     */
    public UpdateBookingDescriptorBuilder withStartTime(String start) {
        descriptor.setStart(new StartTime(start));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code UpdateBookingDescriptor} that we are building.
     */
    public UpdateBookingDescriptorBuilder withEndTime(String end) {
        descriptor.setEnd(new EndTime(end));
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code UpdateBookingDescriptor} that we are building.
     */
    public UpdateBookingDescriptorBuilder withNotes(String notes) {
        descriptor.setNote(new Notes(notes));
        return this;
    }

    public UpdateBookingDescriptor build() {
        return descriptor;
    }
}
