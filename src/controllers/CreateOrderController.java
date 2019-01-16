package controllers;

import java.net.URL;
import models.Table;
import models.Booking;
import models.MenuItem;
import javafx.fxml.FXML;
import utilities.Validation;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import utilities.MenuItemStringConverter;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateOrderController implements Initializable {


    private Table table;
    private Booking booking;
    private NumberValidator numberValidator = new NumberValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @FXML private JFXTextField quantity;
    @FXML private JFXComboBox<MenuItem> itemList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Add menu items to for use in the combobox
        for (MenuItem menuItem : restaurant.getMenu().getMenuItems()) {
            this.itemList.getItems().add(menuItem);
        }

        itemList.setConverter(new MenuItemStringConverter());

        requiredValidator.setMessage("This field is required");
        numberValidator.setMessage("Enter a valid number");

        itemList.getValidators().addAll(requiredValidator);
        quantity.getValidators().addAll(requiredValidator, numberValidator);

        Validation.addValidationListener(quantity);

    }

    /**
     * Set the required data needed for the controller
     *
     * @param table the table the order is for
     * @param booking the current booking at the table
     */
    void setData(Table table, Booking booking) {
        this.table = table;
        this.booking = booking;
    }

    /**
     * Go back to the view orders screen
     *
     */
    @FXML
    public void goBack() { dbc.viewOrders(this.booking); }

    /**
     * Add an order to a table
     *
     */
    public void addOrder() {
        if(!itemList.validate()) return;
        MenuItem item = itemList.getValue();
        Integer qty = Integer.valueOf(quantity.getText());
        this.table.addOrder(item, qty);
        this.goBack();
    }

}