package team.easytravel.model.listmanagers.packinglistitem;

import static java.util.Objects.requireNonNull;

import team.easytravel.commons.util.AppUtil;

/**
 * Represents an PackingListItem's quantity in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemName {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS = "Name must be made up of alphanumeric words that is not more than "
            + "than 30 characters long.";


    // Allows for 30 alphanumeric characters.
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[\\p{Alnum}\\s]{1,30}$";

    public final String value;

    /**
     * Constructs a {@code ItemName}.
     *
     * @param name a valid name.
     */
    public ItemName(String name) {
        requireNonNull(name);
        AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        String[] arr = name.split("\\s+");
        StringBuilder cap = new StringBuilder();
        for (String str : arr) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            cap.append(str).append(' ');
        }
        value = cap.substring(0, cap.length() - 1);
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && value.equals(((ItemName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
