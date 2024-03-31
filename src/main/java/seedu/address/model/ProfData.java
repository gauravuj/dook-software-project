package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents the entire professor data. Contains the data of the professors and bookings in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ProfData implements ReadOnlyAddressBook {
    private final UniquePersonList professors;
    private final UniqueBookingList bookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        professors = new UniquePersonList();
        bookings = new UniqueBookingList();
    }

    public ProfData() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ProfData(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.professors.setPersons(persons);
    }

    /**
     * Replaces the contents of the bookings list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setBookings(bookings);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setBookings(newData.getBookingList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return professors.contains(person);
    }
    public void addPerson(Person p) {
        professors.add(p);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("professors", professors)
                .add("bookings", bookings)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return professors.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfData)) {
            return false;
        }

        ProfData otherProfData = (ProfData) other;
        return professors.equals(otherProfData.professors);
    }

    @Override
    public int hashCode() {
        return professors.hashCode();

    }
}
