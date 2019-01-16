package tests;

import models.Booking;
import models.MenuItem;
import models.Restaurant;
import models.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();

        restaurant.addTable(new Table(1, 4));
        restaurant.addTable(new Table(2, 9));
        restaurant.addTable(new Table(3, 8));
        restaurant.addTable(new Table(4, 2));

        restaurant.addMenuItem(new MenuItem("Tea", 2.0));
        restaurant.addMenuItem(new MenuItem("Coffee", 3.0));
        restaurant.addMenuItem(new MenuItem("Scone", 3.5));

    }

    @Test
    void checkNumberOfTables() {
        assertEquals(restaurant.getTables().size(), 4);
    }

    @Test
    void findingATableIsNotNull() {
        Booking booking = new Booking("Jane", 2, "17:30", 2.0);
        booking = restaurant.findSuitableTable(booking, null);
        assertNotNull(booking);
    }

    @Test
    void ordersAreAddedToCurrentBooking() {
        Booking booking = new Booking("Jane", 2, "17:30", 2.0);
        booking = restaurant.findSuitableTable(booking, null);
        Table table = restaurant.getTableById(booking.getTableNumber());
        table.checkIn(booking);
        MenuItem scone = restaurant.getMenu().getItemByName("Scone");
        MenuItem tea = restaurant.getMenu().getItemByName("Tea");
        table.addOrder(scone, 2);
        table.addOrder(tea, 2);
        assertFalse(table.getOrderItems().isEmpty());
    }

    @Test
    void checkInOfABookingChecksOutOtherBookings() {
        Booking booking1 = new Booking("Jane", 2, "17:30", 0.5);
        booking1 = restaurant.findSuitableTable(booking1, null);
        Table table1 = restaurant.getTableById(booking1.getTableNumber());
        table1.checkIn(booking1);
        Booking booking2 = new Booking("John", 2, "17:30", 0.5);
        booking2 = restaurant.findSuitableTable(booking2, null);
        Table table2 = restaurant.getTableById(booking2.getTableNumber());
        table2.checkIn(booking2);
        assertFalse(booking1.isCurrent());
    }

    @Test
    void ordersAddedToPurchasesOnBookingCheckout() {
        Booking booking = new Booking("Jane", 2, "17:30", 2.0);
        booking = restaurant.findSuitableTable(booking, null);
        Table table = restaurant.getTableById(booking.getTableNumber());
        table.checkIn(booking);
        MenuItem scone = restaurant.getMenu().getItemByName("Scone");
        MenuItem tea = restaurant.getMenu().getItemByName("Tea");
        table.addOrder(scone, 2);
        table.addOrder(tea, 2);
        table.checkOut(booking);
        assertTrue(table.getOrderItems().isEmpty());
        assertFalse(table.getPurchaseItems().isEmpty());
    }

    @Test
    void fullBookedRestaurantCantCreateNewBooking() {
        this.makeAllBookingSlotsUnavailable(restaurant);
        Booking booking = new Booking("Jane", 2, "17:30", 2.0);
        assertNull(restaurant.findSuitableTable(booking, null));
    }

    @Test
    void ifNoTableOfTheSameSizeIsFoundTheNextBestOptionIsFound() {
        restaurant = new Restaurant();
        restaurant.addTable(new Table(1, 6));
        restaurant.addTable(new Table(2, 7));
        restaurant.addTable(new Table(3, 8));
        restaurant.addTable(new Table(4, 9));

        Booking booking = new Booking("Jane", 2, "17:30", 2.0);
        booking = restaurant.findSuitableTable(booking, null);
        assertEquals(new Integer(1), booking.getTableNumber());
    }

    private void makeAllBookingSlotsUnavailable(Restaurant restaurant) {
        for(Table table : restaurant.getTables()) {
            for(int i = 0; i < table.getSlots().length; i++) {
                table.setSlot(i, true);
            }
        }
    }



}
