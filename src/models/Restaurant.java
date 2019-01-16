package models;

import collections.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static utilities.Utilities.*;

/**
 * Restaurant class
 *
 * This is the main class that holds the list of all the tables and the menu.
 *
 * Each table then holds a booking and purchase and order lists
 *
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Restaurant {

    private Menu menu;
    private LinkedList<Table> tables;

    public Restaurant() {
        this.menu = new Menu();
        this.tables = new LinkedList<>();
    }

    /**
     * Add a table to the restaurant
     *
     * @param table the table to be added
     */
    public void addTable(Table table) {
        this.tables.add(table);
    }

    /**
     * Delete a table from the restaurant
     *
     * @param table the table to be deleted
     */
    public void deleteTable(Table table) { this.tables.delete(table); }

    /**
     * Add a menu item to the restaurant menu
     *
     * @param item to be added to the menu
     */
    public void addMenuItem(MenuItem item) {
        this.menu.add(item);
    }

    /**
     * Delete a menu item from the restaurant menu
     *
     * @param item to be deleted from the menu
     */
    public void deleteMenuItem(MenuItem item) { this.menu.remove(item); }

    /**
     * Search method used to find a suitable booking at a restaurant.
     * The method takes in a temporary booking.
     *
     * The method searches through the tables to find a table large enough
     * with the right amount of time slots to accommodate the guests at the table.
     *
     * This method searches for tables recursively, updating the tolerance by one each time.
     * The reason for this is that we don't want to seat a party size of 2 at a table with 9 seats.
     * So we first check for a table and if it there is one more seat than the party size, this is acceptable.
     * If no table is found, we call through the method again, and update the tolerance by 1.
     *
     * The method returns the complete booking or null if it was not possible to place a booking
     *
     * @param booking the temporary booking to be assigned to a table
     * @param tolerance the amount of extra seats allowed for a booking
     * @return The booking that is placed at one of the tables
     */
    public Booking findSuitableTable(Booking booking, Integer tolerance) {

        tolerance = (tolerance != null) ? tolerance : 0;

        // convert the booking time into the index of the available booking slot
        int required =  lookupBookingTime(booking.getBookingTime());
        // convert the duration in hours to the equivalent in 30 minute slots
        int duration = getNumberOfSlotsByBookingDuration(booking.getBookingDuration());

        for(Table table : this.tables) {
            // if the table is too small for the booking or
            // if the table is too large for the booking
            // skip to the next table
            if(table.getSeats() < booking.getNumberOfGuests() || (table.getSeats() - booking.getNumberOfGuests()) > tolerance) continue;

            // get the index of the starting point and the index of the ending point
            // in the booking slot array
            int[] range = table.findOpenSlot(required, duration);

            if(range != null) {
                booking.setTableNumber(table.getUuid());
                table.getBookings().add(booking);
                table.reserveSlots(range[0], range[1]);
                return booking;
            }

        }

        if(tolerance <= 4) return findSuitableTable(booking, tolerance + 1);

        return null;
    }

    /**
     * Get a table in the restaurant from the table id
     *
     * @param id the id of the table being searched for
     * @return the table being searched for
     */
    public Table getTableById(int id) {
        for(Table table : this.tables) {
            if(table.getUuid() == id) return table;
        }
        return null;
    }

    /**
     * This method returns an observable list of all the tables for
     * use in JavaFX views
     *
     * @return list of tables
     */
    public ObservableList<Table> getAllTables() {

        ObservableList<Table> tableList = FXCollections.observableArrayList();

        for(Table table : tables) {
            tableList.add(table);
        }

        return tableList;

    }

    /**
     * This method returns an observable list of bookings for
     * use in JavaFX views
     *
     * @return list of bookings
     */
    public ObservableList<Booking> getAllBookings() {

        ObservableList<Booking> bookingList = FXCollections.observableArrayList();

        for(Table table : tables) {
            for(Booking booking : table.getBookings()) {
                bookingList.add(booking);
            }
        }

        return bookingList;

    }

    /**
     * This method returns an observable list of settled purchase items for
     * use in JavaFX views
     *
     * @return list of settled purchase items
     */
    public ObservableList<Purchase> getSettledPurchases() {
        ObservableList<Purchase> settledPurchases = FXCollections.observableArrayList();

        Double total = 0.0;

        for(Table table : tables) {
            total = total + table.getSettledTotal();
            settledPurchases.addAll(table.getPurchaseItems());
        }

        settledPurchases.add(new Purchase(new MenuItem("Total", total), 1));

        return settledPurchases;
    }

    /**
     * This method returns an observable list of pending purchase items for
     * use in JavaFX views
     *
     * @return list of pending purchase items
     */
    public ObservableList<Purchase> getPendingPurchases() {
        ObservableList<Purchase> pendingPurchases = FXCollections.observableArrayList();

        Double total = 0.0;

        for(Table table : tables) {
            total = total + table.getPendingTotal();
            pendingPurchases.addAll(table.getOrderItems());
        }

        pendingPurchases.add(new Purchase(new MenuItem("Total", total), 1));

        return pendingPurchases;
    }

    /**
     * Get the restaurant menu
     *
     * @return the restaurant menu
     */
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Reset the restaurant back to initial state
     * This method could be simplified by
     * creating a new Restaurant, however the method was created
     * to show the LinkedList reset method
     *
     */
    public void reset() {
        this.tables.reset();
        this.menu.getMenuItems().reset();
    }

    /**
     * Get the list of tables in the restaurant
     *
     * @return the list of tables in the restaurant
     */
    public LinkedList<Table> getTables() {
        return this.tables;
    }


}
