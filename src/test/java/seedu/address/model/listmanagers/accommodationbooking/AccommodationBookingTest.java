package seedu.address.model.listmanagers.accommodationbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.attributes.Location;


class AccommodationBookingTest {

    private final AccommodationBooking accommodationBooking =
            new AccommodationBooking(new AccommodationName("RitzCarlton"),
                    new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"));

    @Test
    public void constructorInvalidNameThrowsIllegalArgumentException() {
        String invalidName = "";
        Integer invalidStartDay = -100;
        Integer invalidEndDay = -200;
        String invalidLocation = "";
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new AccommodationBooking(new AccommodationName(invalidName),
                new Location(invalidLocation), new Day(invalidStartDay), new Day(invalidEndDay),
                new Remark(invalidRemark)));
    }

    @Test
    public void getAccommodationName() {
        // Correct case
        assertEquals(new AccommodationName("RitzCarlton"),
                new AccommodationBooking(new AccommodationName("RitzCarlton"),
                        new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"))
                        .getAccommodationName());
        //Different case
        assertNotEquals(new AccommodationName("MBS"), new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"))
                .getAccommodationName());
    }

    @Test
    public void getLocation() {
        // Correct case
        assertEquals(new Location("Marina Bay"), accommodationBooking.getLocation());
        //Different case
        assertNotEquals(new Location("Jurong"), accommodationBooking.getLocation());
    }

    @Test
    public void getStartDay() {
        //Correct case
        assertEquals(new Day(1), accommodationBooking.getStartDay());

        //Different case
        assertNotEquals(new Day(10), accommodationBooking.getStartDay());

    }

    @Test
    public void getEndDay() {
        //Correct case
        assertEquals(new Day(5), accommodationBooking.getEndDay());

        //Different case
        assertNotEquals(new Day(200), accommodationBooking.getEndDay());
    }

    @Test
    public void getRemark() {
        // Correct case
        assertEquals(new Remark("Expensive Hotel"), accommodationBooking.getRemark());

        //Different case
        assertNotEquals(new Remark("Cheap Hotel"), accommodationBooking.getRemark());
    }

    @Test
    public void isDayValid() {
        assertTrue(accommodationBooking.isDayValid(accommodationBooking.getStartDay(),
                accommodationBooking.getEndDay()));

        //TODO Come out with the false method
    }

    @Test
    public void equals() {
        AccommodationBooking identicalBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"));

        AccommodationBooking differentBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(3), new Day(10), new Remark("Expensive Hotel"));

        //Correct case
        assertEquals(identicalBooking, accommodationBooking);

        //Different case
        assertNotEquals(accommodationBooking, differentBooking);

    }

    @Test
    public void testHashCode() {
        AccommodationBooking identicalBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"));

        // Same case
        assertEquals(identicalBooking.hashCode(), accommodationBooking.hashCode());

        AccommodationBooking differentBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(3), new Day(10), new Remark("Expensive Hotel"));

        //Different case
        assertNotEquals(accommodationBooking.hashCode(), differentBooking.hashCode());

    }

    @Test
    public void testToString() {
        AccommodationBooking identicalBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"));

        //Same case
        assertEquals(identicalBooking.toString(), accommodationBooking.toString());

        AccommodationBooking differentBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(10), new Remark("Expensive Hotel"));

        //Different case
        assertNotEquals(accommodationBooking.toString(), differentBooking.toString());
    }

    @Test
    public void isSame() {
        AccommodationBooking identicalBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(5), new Remark("Expensive Hotel"));

        assertTrue(identicalBooking.isSame(accommodationBooking));

        AccommodationBooking differentBooking = new AccommodationBooking(new AccommodationName("RitzCarlton"),
                new Location("Marina Bay"), new Day(1), new Day(10), new Remark("Expensive Hotel"));

        assertFalse(accommodationBooking.isSame(differentBooking));


    }
}