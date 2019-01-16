package controllers;

import java.net.URL;
import models.Booking;
import models.Restaurant;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import static controllers.DashboardController.*;

public class BookingsController implements Initializable {

    @FXML private TableView<Booking> bookings;
    @FXML private TableColumn<Booking, String> customerName;
    @FXML private TableColumn<Booking, Integer> tableNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurant tables = DashboardController.restaurant;
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        bookings.setItems(tables.getAllBookings());
    }

    /**
     * Go to the create booking view
     *
     */
    @FXML
    public void createBooking() {
        dbc.createBooking();
    }

    /**
     * View a booking
     *
     */
    @FXML
    private void viewBooking() {
        Booking booking = bookings.getSelectionModel().getSelectedItem();
        if (booking != null) dbc.viewBooking(booking);
    }

    /**
     * Display the confirm delete modal to cancel a booking
     *
     */
    public void cancelBooking() {
        Booking booking = bookings.getSelectionModel().getSelectedItem();
        if (booking != null) {
            String message = "Are you sure you want to cancel the booking for\n"
                    + booking.getCustomerName() + " at "
                    + booking.getBookingTime() + "?";
            dbc.displayAlertBox("Cancel Booking", message, booking, "booking", null, null);
        }
    }

}
