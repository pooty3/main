package team.easytravel.model.listmanagers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.util.uniquelist.exceptions.DuplicateElementException;
import team.easytravel.testutil.Assert;
import team.easytravel.testutil.accommodationbooking.TypicalAccommodation;

class AccommodationBookingManagerTest {

    private final AccommodationBookingManager accommodationBookingManager = new AccommodationBookingManager();


    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), accommodationBookingManager.getAccommodationBookingList());
    }

    @Test
    public void resetDataNullThrowsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> accommodationBookingManager.resetData(null));
    }

    @Test
    public void resetDataWithValidReadOnlyAccommodationBookingManagerReplacesData() {
        AccommodationBookingManager newData = TypicalAccommodation.getTypicalAccommodationManager();
        accommodationBookingManager.resetData(newData);
        assertEquals(newData, accommodationBookingManager);
    }

    @Test
    public void resetDataWithDuplicateAccommodationBookingThrowsDuplicateElementException() {
        List<AccommodationBooking> newAccommodations = Arrays.asList(TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL,
                TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL);
        AccommodationBookingManagerStub newData = new AccommodationBookingManagerStub(newAccommodations);
        Assert.assertThrows(DuplicateElementException.class, () -> accommodationBookingManager.resetData(newData));
    }

    @Test
    public void hasAccommodationBookingNullAccommodationBookingThrowsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> accommodationBookingManager
                .hasAccommodationBooking(null));
    }

    @Test
    public void hasAccommodationBookingNotInAccommodationBookingManagerReturnsFalse() {
        assertFalse(accommodationBookingManager.hasAccommodationBooking(
                TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL));
    }

    @Test
    public void hasAccommodationBookingInAccommodationBookingManagerReturnsTrue() {
        accommodationBookingManager.addAccommodationBooking(TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL);
        assertTrue(accommodationBookingManager
                .hasAccommodationBooking(TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL));
    }

    @Test
    public void getAccommodationBookingModifyListThrowsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> accommodationBookingManager
                .getAccommodationBookingList().remove(0));
    }

    @Test
    public void getStatusNoAccommodation() {
        final int tripnumdays = 7;
        String result;
        StringBuilder stringBuilder = new StringBuilder("[❌] Accommodation for night");
        boolean accommodationBookingIsOk = true;
        boolean[] accommodationDayCheck = new boolean[tripnumdays];
        accommodationBookingManager.getAccommodationBookingList()
                .forEach(x -> IntStream.range(x.getStartDay().value, x.getEndDay().value)
                        .forEach(y -> accommodationDayCheck[y - 1] = true));
        for (int i = 0; i < tripnumdays - 1; i++) {
            if (!accommodationDayCheck[i]) {
                accommodationBookingIsOk = false;
                stringBuilder.append(" ").append(i + 1).append(",");
            }
        }
        if (accommodationBookingIsOk) {
            result = "[✔] Accommodation Booking is completed for all nights.";
        } else {
            result = stringBuilder.append(" is/are missing").toString();
        }
        assertEquals(result, accommodationBookingManager.getStatus(tripnumdays));
    }

    @Test
    public void getStatusAllAccommodationSettled() {
        accommodationBookingManager.addAccommodationBooking(TypicalAccommodation.ACCOMMODATION_BOOKING_HOTEL);
        final int tripnumdays = 3;
        String result;
        StringBuilder stringBuilder = new StringBuilder("[❌] Accommodation for night");
        boolean accommodationBookingIsOk = true;
        boolean[] accommodationDayCheck = new boolean[tripnumdays];
        accommodationBookingManager.getAccommodationBookingList()
                .forEach(x -> IntStream.range(x.getStartDay().value, x.getEndDay().value)
                        .forEach(y -> accommodationDayCheck[y - 1] = true));
        for (int i = 0; i < tripnumdays - 1; i++) {
            if (!accommodationDayCheck[i]) {
                accommodationBookingIsOk = false;
                stringBuilder.append(" ").append(i + 1).append(",");
            }
        }
        if (accommodationBookingIsOk) {
            result = "[✔] Accommodation Booking is completed for all nights.";
        } else {
            result = stringBuilder.append(" is/are missing").toString();
        }
        assertEquals(result, accommodationBookingManager.getStatus(tripnumdays));
    }

    /**
     * A stub ReadOnlyAccommodationBookingManager whose accommodation list can violate interface constraints.
     */
    private static class AccommodationBookingManagerStub implements ReadOnlyAccommodationBookingManager {
        private final ObservableList<AccommodationBooking> accommodationBookings =
                FXCollections.observableArrayList();

        AccommodationBookingManagerStub(Collection<AccommodationBooking> accommodationBookings) {
            this.accommodationBookings.setAll(accommodationBookings);
        }

        @Override
        public ObservableList<AccommodationBooking> getAccommodationBookingList() {
            return accommodationBookings;
        }
    }
}
