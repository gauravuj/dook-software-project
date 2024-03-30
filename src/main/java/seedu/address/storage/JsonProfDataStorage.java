package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * JsonProfDataStorage class handles the reading of Professor data from a JSON file and converting it into the internal
 * representation used by ProfBook.
 */
public class JsonProfDataStorage implements ProfDataStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    /**
     * Constructs a {@code JsonProfDataStorage} with the specified file path.
     *
     * @param filePath The file path where the JSON data is stored.
     */
    public JsonProfDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProfFilePath() {
        return filePath;
    }


    /**
     * Reads Professor data from the file specified during construction.
     *
     * @return An Optional containing the read ReadOnlyAddressBook if successful, or an empty Optional
     *     if the file is not found or an error occurs during reading.
     * @throws DataLoadingException If there is an error reading from the file.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readProfData() throws DataLoadingException {
        return readProfData(filePath);
    }

    /**
     * Reads Professor data from the specified file path.
     *
     * @param filePath The file path to read the data from.
     * @return An Optional containing the read ReadOnlyAddressBook if successful, or an empty Optional
     *     if the file is not found or an error occurs during reading.
     * @throws DataLoadingException If there is an error reading from the file.
     */
    public Optional<ReadOnlyAddressBook> readProfData(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);
        Optional<JsonSerializableProfData> jsonProfData = JsonUtil.readJsonFile(
                Paths.get(this.getClass().getResource(filePath.toString()).getPath()), JsonSerializableProfData.class);
        if (!jsonProfData.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonProfData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

}
