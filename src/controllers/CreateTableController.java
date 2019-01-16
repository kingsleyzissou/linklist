package controllers;

import java.net.URL;
import models.Table;
import javafx.fxml.FXML;
import utilities.Validation;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateTableController implements Initializable {

    @FXML private JFXTextField tableNumber;
    @FXML private JFXTextField numberOfSeats;

    private NumberValidator numberValidator = new NumberValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requiredValidator.setMessage("This field is required");
        numberValidator.setMessage("Enter a valid number");
        tableNumber.getValidators().addAll(requiredValidator, numberValidator);
        numberOfSeats.getValidators().addAll(requiredValidator, numberValidator);
        Validation.addValidationListener(tableNumber);
        Validation.addValidationListener(numberOfSeats);
    }

    /**
     * Add a table to the restaurant
     *
     */
    public void addTable() {
        Integer tableID = Integer.valueOf(tableNumber.getText());
        Integer seats = Integer.valueOf(numberOfSeats.getText());
        restaurant.addTable(new Table(tableID, seats));
        goBack();
    }

    /**
     * Go back to view all tables
     *
     */
    public void goBack() {
        dbc.viewTables();
    }



}
