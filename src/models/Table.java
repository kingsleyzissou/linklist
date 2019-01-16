package models;

import collections.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static utilities.Utilities.*;

/**
 * Table class
 *
 * Class to manage individual tables
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Table {

    private Integer uuid;
    private Integer seats;
    private LinkedList<Booking> bookings;
    private Boolean slots[] = new Boolean[10];
    private LinkedList<Purchase> orderItems;
    private LinkedList<Purchase> purchaseItems;

    public Table(Integer uuid, Integer seats){
        this.setUuid(uuid);
        this.setSeats(seats);
        this.initialiseSlots();
        this.setBookings(new LinkedList<>());
        this.setOrderItems(new LinkedList<>());
        this.setPurchaseItems(new LinkedList<>());
    }

    /**
     * Get the unique table identifier
     * @return uuid the table identifier
     */
    public Integer getUuid() {
        return uuid;
    }

    /**
     * Set the table's unique identifier/table number
     *
     * @param uuid the unique identifier for the table
     */
    private void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the number of seats the table has
     *
     * @return the number of seats at the table
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * Initialise the number of seats available at the table
     *
     * @param seats the number of seats available at the table
     */
    private void setSeats(Integer seats) {
        this.seats = seats;
    }

    /**
     * Get the bookings for a given table
     *
     * @return the list of bookings at a table
     */
    LinkedList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Initialise table bookings
     *
     * @param bookings the list of bookings at a table
     */
    private void setBookings(LinkedList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Get the list of time slots at the restaurant
     *
     * @return the list of slots at the restaurant
     */
    public Boolean[] getSlots() {
        return this.slots;
    }

    /**
     * Initialise the available time slots for the restaurant
     *
     */
    public void setSlot(int index, Boolean value) {
        this.slots[index] = value;
    }

    /**
     * Set the running total of  order items made for a table for a current booking
     *
     * @param orderItems the list of items ordered for a current booking
     */
    public void setOrderItems(LinkedList<Purchase> orderItems) {
        this.orderItems = orderItems;
    }


    /**
     * Set the running total of settled purchases made for a table
     *
     * @param purchaseItems the list of items purchased at a table
     */
    public void setPurchaseItems(LinkedList<Purchase> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    /**
     * Initialise the array of slots to false,
     * this means that there is no booking for each time slot
     *
     */
    private void initialiseSlots() {
        for(int i = 0; i < slots.length; i++) {
            this.setSlot(i, false);
        }
    }

    /**
     * Check in a booking and set the booking as active/current
     *
     * @param booking the booking that is to be checked in
     */
    public void checkIn(Booking booking) {
        if(booking.isCurrent()) return;
        for(Booking b : bookings) {
            if(b.isCurrent())
                this.checkOut(b);
        }
        booking.setCurrent(true);
    }

    /**
     * Check out a booking and add the order items to the running total for the table
     *
     * @param booking the booking to be checked out
     */
    public void checkOut(Booking booking) {
        this.addOrdersToPurchases();
        this.setOrderItems(new LinkedList<>());
        booking.setCurrent(false);
        this.remove(booking);
    }

    /**
     * Add all the order items from a table to the list of settled
     * purchase items
     *
     */
    private void addOrdersToPurchases() {
        if(!this.orderItems.isEmpty())
            this.purchaseItems.merge(this.orderItems);
    }

    /**
     * Add an order to the order list
     *
     * @param item the item being add
     * @param quantity the quantity of the item being added
     */
    public void addOrder(MenuItem item, Integer quantity) {
        for(Purchase order : orderItems) {
            if (order.getMenuItem().equals(item)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
        this.orderItems.add(new Purchase(item, quantity));
    }

    /**
     * Remove a booking from the table
     *
     * @param booking the booking to be removed
     */
    public void remove(Booking booking) {
        this.bookings.delete(booking);
    }

    /**
     * Cancel a booking from the table
     *
     * @param booking the booking to be cancelled
     */
    public void cancelBooking(Booking booking) {
        int bookingTime = lookupBookingTime(booking.getBookingTime());
        int duration = getNumberOfSlotsByBookingDuration(booking.getBookingDuration());

        for(int i = bookingTime; i < bookingTime + duration; i++ )
            this.setSlot(i, false);

        this.bookings.delete(booking);
    }

    /**
     * Delete an order the current booking at a table
     *
     * @param order the order item to be deleted
     */
    public void deleteOrder(Purchase order) {
        this.orderItems.delete(order);
    }

    /**
     * This method checks the available booking slots at a table.
     *
     * If there are enough adjacent slots at a table to accommodate the sought after booking,
     * the method returns the range of slots from the array, otherwise it returns null.
     *
     * @param required the required number of slots needed
     * @param duration the duration of the booking
     * @return the range of slots in which to make the booking
     */
    protected int[] findOpenSlot(int required, int duration) {
        int adjacentCount = 0;
        Boolean[] slots = this.getSlots();

        if(required == -1) return null;

        if(required + duration > this.getSlots().length) return null;

        for(int i = required; i < slots.length; i++) {
            adjacentCount = (slots[i]) ?  0 : ++adjacentCount;
            if(adjacentCount == 0) continue;
            if(adjacentCount == duration) {
                return new int[]{(i - adjacentCount + 1), adjacentCount};
            }
        }

        return null;
    }

    /**
     * This method marks each slot in the available booking slots as reserved (i.e true)
     *
     * @param start the starting point in the booking slots array to be marked as reserved
     * @param end the end point in the booking slots array to be marked as reserved
     */
    public void reserveSlots(int start, int end) {
        for(int i = start; i <= end; i++) {
            Boolean slots[] = this.getSlots();
            slots[i] = true;
        }
    }

    /**
     * This method returns an obserable list of order items for
     * use in JavaFX views
     *
     * @return orderItem list
     */
    public ObservableList<Purchase> getOrderItems() {
        ObservableList<Purchase> orderList = FXCollections.observableArrayList();
        for(Purchase item : orderItems) {
            orderList.add(item);
        }
        return orderList;
    }

    /**
     * This method returns an observable list of purchase items for
     * use in JavaFX views
     *
     * @return purchaseItems list
     */
    public ObservableList<Purchase> getPurchaseItems() {

        ObservableList<Purchase> purchaseList = FXCollections.observableArrayList();

        for(Purchase item : purchaseItems) {
            purchaseList.add(item);
        }

        return purchaseList;

    }

    /**
     * The running total of all the items purchased at a table
     *
     * @return total the cumulative total of all items purchased at a table
     */
    public Double getSettledTotal() {
        Double total = 0.0;
        for(Purchase purchase : purchaseItems) {
            total = total + (purchase.getQuantity() * purchase.getMenuItem().getPrice());
        }
        return total;
    }

    /**
     * The running total of items for the current booking at a table
     *
     * @return total the cumulative total of all items for the current at a table
     */
    public Double getPendingTotal() {
        Double total = 0.0;
        for(Purchase order : orderItems) {
            total = total + (order.getQuantity() * order.getMenuItem().getPrice());
        }
        return total;
    }
}
