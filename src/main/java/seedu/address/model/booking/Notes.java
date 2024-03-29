package seedu.address.model.booking;

/**
 * Represents a Booking's notes in the address book.
 * Guarantees: immutable
 */
public class Notes {

    public static final String MESSAGE_CONSTRAINTS = "Notes cannot be comprised of spaces.";

    public final String notes;

    /**
     * Constructs a {@code Notes}.
     *
     * @param notes A valid note.
     */
    public Notes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns true if a given string is a valid note.
     *
     * Must not be empty or made of spaces.
     */
    public static boolean isValidNote(String test) {
        return !test.isEmpty();
    }

    @Override
    public String toString() {
        return this.notes;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Notes)) {
            return false;
        }
        Notes otherNote = (Notes) other;
        return this.notes.equals(otherNote.notes);
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
