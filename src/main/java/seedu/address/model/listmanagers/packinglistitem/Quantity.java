package seedu.address.model.listmanagers.packinglistitem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

/**
 * Represents an PackingListItem's quantity in the packing list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Quantity {
    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only be a positive integer, from 1 onwards";

    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 1;
    public static final Predicate<Integer> VALIDATION_PREDICATE = i -> i >= MIN_VALUE && i <= MAX_VALUE;

    public final Integer value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity a valid quantity.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.value = quantity;
    }

    /**
     * Returns true if a given integer is a valid quantity number.
     */
    public static boolean isValidQuantity(Integer test) {
        return VALIDATION_PREDICATE.test(test);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value.equals(((Quantity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}