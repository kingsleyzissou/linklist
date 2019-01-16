package controllers;

import models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import static controllers.DashboardController.*;

public class AlertBoxController {

    private Table table;
    private String type;
    private Object object;
    private Booking booking;

    @FXML private Label message;
    @FXML private Button confirm;

    /**
     * Set the data required for an alert box.
     * The alert box is used to display alert, primarily for deleting items.
     *
     * @param message the message to be displayed
     * @param object the object to be deleted
     * @param type the type of object (polymorphic)
     * @param booking optional booking
     * @param table optional table
     */
    void setData(String message, Object object, String type, Booking booking, Table table) {
        this.type = type;
        this.object = object;
        this.message.setText(message);
        this.table = table;
        this.booking = booking;
        if(type.equals("warning")) confirm.setManaged(false);
    }

    /**
     * Closes the modal dialog
     *
     */
    public void closeDialog() { modal.close(); }

    /**
     * Carries out the request based on the type of object
     * being actioned
     *
     */
    public void confirmRequest() {
        if(type.equals("table"))
            deleteTable((Table) object);
        if(type.equals("order"))
            deleteOrder((Purchase) object);
        if(type.equals("booking"))
            cancelBooking((Booking) object);
        if(type.equals("menuItem"))
            deleteMenuItem((MenuItem) object);
        if(type.equals("restaurant"))
            resetRestaurant();
    }

    /**
     * Reset the restaurant back to initial state
     *
     */
    private void resetRestaurant() {
        restaurant.reset();
        closeDialog();
        dbc.loadHomeScene();
    }

    /**
     * Deletes an order from the table of a current booking
     *
     * @param purchase the item to be removed from the order
     */
    private void deleteOrder(Purchase purchase) {
        this.table.deleteOrder(purchase);
        closeDialog();
        dbc.viewOrders(this.booking);
    }

    /**
     * Cancel a booking/reservation at a table
     *
     * @param booking to be cancelled
     */
    private void cancelBooking(Booking booking) {
        Table table = restaurant.getTableById(booking.getTableNumber());
        table.cancelBooking(booking);
        closeDialog();
        dbc.viewBookings();
    }

    /**
     * Delete/remove a table from a restaurant
     *
     * @param table to be removed
     */
    private void deleteTable(Table table) {
        restaurant.deleteTable(table);
        closeDialog();
        dbc.viewTables();
    }

    /**
     * Remove a menu item from the restaurant menu
     *
     * @param item to be removed
     */
    private void deleteMenuItem(MenuItem item) {
        restaurant.deleteMenuItem(item);
        closeDialog();
        dbc.viewMenu();
    }

}
