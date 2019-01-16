package controllers;

import java.net.URL;
import models.Booking;
import javafx.fxml.FXML;
import utilities.Validation;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateBookingController implements Initializable {

    @FXML private JFXTextField customerName;
    @FXML private JFXTextField numberOfGuests;
    @FXML private JFXComboBox<String> bookingTime;
    @FXML private JFXTextField bookingDuration;

    private NumberValidator numberValidator = new NumberValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookingTime.getItems().addAll(
                "17:00", "17:30", "18:00", "18:30", "19:00", "19:30","20:00", "20:30", "21:00", "21:30"
        );

        requiredValidator.setMessage("This field is required");
        numberValidator.setMessage("Enter a valid number");

        customerName.getValidators().addAll(requiredValidator);
        bookingTime.getValidators().addAll(requiredValidator);
        numberOfGuests.getValidators().addAll(requiredValidator, numberValidator);
        bookingDuration.getValidators().addAll(requiredValidator, numberValidator);

        Validation.addValidationListener(customerName);
        Validation.addValidationListener(numberOfGuests);
        Validation.addValidationListener(bookingDuration);
    }

    /**
     * Add a booking to a table
     *
     */
    public void addBooking() {
        if(!bookingTime.validate()) {
            System.out.println("Here");
            return;
        }
        String customer = customerName.getText();
        String time = bookingTime.getValue();
        Integer seats = Integer.valueOf(numberOfGuests.getText());
        Double duration = Double.valueOf(bookingDuration.getText());
        Booking booking = restaurant.findSuitableTable(new Booking(customer, seats, time, duration), null);
        if (booking != null) {
            this.goBack();
            return;
        }
        errorMessage();
    }

    /**
     * Validate the form elements before adding a booking
     *
     * @return false if the form has not been validated, true otherwise
     */
    private boolean validateForm() {
        if(!bookingTime.validate() || !customerName.validate() || !bookingDuration.validate() || numberOfGuests.validate()) {
            return false;
        }
        return true;
    }

    /**
     * Go back to the bookings view
     *
     */
    public void goBack() {
        dbc.viewBookings();
    }


    /**
     * Display error message modal before deleting a booking
     *
     */
    private void errorMessage() {
        String message = "There are no bookings available for the selected time,\n party size & duration. Please re-book.";
        dbc.displayAlertBox("Booking unsuccessful", message, null, "warning", null, null);
    }

}
