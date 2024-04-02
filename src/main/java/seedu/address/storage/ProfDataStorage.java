package seedu.address.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.ProfData}.
 */
public interface ProfDataStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getProfFilePath();

    Optional<ReadOnlyAddressBook> readProfData() throws DataLoadingException;

    Optional<ReadOnlyAddressBook> readProfData(Path filePath) throws DataLoadingException;


}
