package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NotesTest {

    @Test
    public void constructor_validNote_success() {
        Notes notes = new Notes("Valid note");
        assertEquals("Valid note", notes.toString());
    }

    @Test
    public void isValidNote_validNote_true() {
        assertTrue(Notes.isValidNote("Valid note"));
    }

    @Test
    public void isValidNote_emptyNote_false() {
        assertFalse(Notes.isValidNote(""));
    }

    @Test
    public void isValidNote_onlySpaces_false() {
        assertFalse(Notes.isValidNote("   "));
    }

    @Test
    public void equals_sameObject_true() {
        Notes notes = new Notes("Note");
        assertTrue(notes.equals(notes));
    }

    @Test
    public void equals_equalNotes_true() {
        Notes notes1 = new Notes("Note");
        Notes notes2 = new Notes("Note");
        assertTrue(notes1.equals(notes2));
    }

    @Test
    public void equals_differentNotes_false() {
        Notes notes1 = new Notes("Note 1");
        Notes notes2 = new Notes("Note 2");
        assertFalse(notes1.equals(notes2));
    }

    @Test
    public void equals_differentObjects_false() {
        Notes notes = new Notes("Note");
        assertFalse(notes.equals("Note"));
    }

    @Test
    public void hashCode_sameNotes_equal() {
        Notes notes1 = new Notes("Note");
        Notes notes2 = new Notes("Note");
        assertEquals(notes1.hashCode(), notes2.hashCode());
    }

    @Test
    public void hashCode_differentNotes_notEqual() {
        Notes notes1 = new Notes("Note 1");
        Notes notes2 = new Notes("Note 2");
        assertNotEquals(notes1.hashCode(), notes2.hashCode());
    }
}
