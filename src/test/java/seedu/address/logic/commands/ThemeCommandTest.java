package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Theme;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code ThemeCommand}.
 */
public class ThemeCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_changeThemeLightToDark_success() {
        // initialize theme to light
        model.setTheme(Theme.LIGHT);

        Theme expectedTheme = Theme.DARK;
        ThemeCommand themeCommand = new ThemeCommand(expectedTheme);

        String expectedMessage = ThemeCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager();
        expectedModel.setTheme(expectedTheme);

        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getTheme(), model.getTheme());
    }

    @Test
    public void execute_changeSameTheme_failure() {
        // initialize theme to light
        model.setTheme(Theme.LIGHT);

        // test for graceful failure when changing to light theme
        Theme expectedTheme = Theme.LIGHT;
        ThemeCommand themeCommand = new ThemeCommand(expectedTheme);

        String expectedMessage = String.format(ThemeCommand.MESSAGE_FAILURE, Theme.LIGHT);
        assertCommandFailure(themeCommand, model, expectedMessage);
    }

}
