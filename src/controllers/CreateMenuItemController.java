package controllers;

import java.net.URL;
import javafx.fxml.FXML;
import models.MenuItem;
import utilities.Validation;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import static controllers.DashboardController.*;

public class CreateMenuItemController implements Initializable {

    @FXML private JFXTextField name;
    @FXML private JFXTextField price;

    private DoubleValidator doubleValidator = new DoubleValidator();
    private RequiredFieldValidator requiredValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requiredValidator.setMessage("This field is required");
        doubleValidator.setMessage("Enter a valid number");

        name.getValidators().addAll(requiredValidator);
        price.getValidators().addAll(requiredValidator, doubleValidator);

        Validation.addValidationListener(name);
        Validation.addValidationListener(price);
    }

    /**
     * Go back to the menu view
     *
     */
    @FXML
    public void goBack() {
        dbc.viewMenu();
    }

    /**
     * Add a menu item to the menu
     *
     */
    @FXML
    public void addItem() {
        String itemName = name.getText();
        Double itemPrice = Double.valueOf(price.getText());
        restaurant.addMenuItem(new MenuItem(itemName, itemPrice));
        this.goBack();
    }


}
