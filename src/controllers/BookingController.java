package controllers;

import models.Table;
import models.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import static controllers.DashboardController.*;

public class BookingController {

    private Booking booking;
    private Table table;

    @FXML private Label customer;
    @FXML private Label guests;
    @FXML private Label tableNumber;
    @FXML private Label time;
    @FXML private Label duration;
    @FXML private Button orderButton;
    @FXML private Button checkInButton;
    @FXML private Button checkOutButton;

    /**
     * Set the data required for viewing a singular booking
     *
     * @param booking the booking being viewed
     * @param table the table to which the booking has been made
     */
    void setData(Booking booking, Table table) {
        this.table = table;
        this.booking = booking;
        customer.setText("" + booking.getCustomerName());
        guests.setText("" + booking.getNumberOfGuests());
        tableNumber.setText("" + booking.getTableNumber());
        time.setText("" + booking.getBookingTime());
        duration.setText("" + booking.getBookingDuration());
        checkInButton.setVisible(!booking.isCurrent());
        checkOutButton.setVisible(booking.isCurrent());
        orderButton.setVisible(booking.isCurrent());
    }

    /**
     * Check in the booking in order for the correct buttons to be displayed
     *
     */
    @FXML
    public void checkIn() {
        this.table.checkIn(this.booking);
        this.toggleButtons();
    }

    /**
     * Check out the booking in order for the correct buttons to be displayed
     *
     */
    @FXML
    public void checkOut() {
        this.table.checkOut(this.booking);
        this.toggleButtons();
        this.goBack();
    }

    /**
     * Display the correct buttons based on the check in status of a booking
     *
     */
    @FXML
    private void toggleButtons() {
        checkInButton.setVisible(!checkInButton.isVisible());
        checkOutButton.setVisible(!checkOutButton.isVisible());
        orderButton.setVisible(!orderButton.isVisible());
    }

    /**
     * Go back to the bookings view
     *
     */
    @FXML
    public void goBack() { dbc.viewBookings(); }

    /**
     * View the orders for the current booking (only available for the current booking)
     *
     */
    @FXML
    public void viewOrder() { dbc.viewOrders(this.booking); }


    /**
     * Display the confirm delete modal to cancel a booking
     *
     */
    @FXML
    public void cancelBooking() {
        String message = "Are you sure you want to cancel the booking for\n"
                + this.booking.getCustomerName() + " at "
                + this.booking.getBookingTime() + "?";
        dbc.displayAlertBox("Cancel Booking", message, booking, "booking", null, null);
    }

}
