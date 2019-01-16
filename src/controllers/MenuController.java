package controllers;


import models.Menu;
import java.net.URL;
import models.MenuItem;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import static controllers.DashboardController.*;

public class MenuController implements Initializable {

    @FXML private TableView<MenuItem> menuList;
    @FXML private TableColumn<MenuItem, String> item;
    @FXML private TableColumn<MenuItem, Double> price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Menu menu = DashboardController.restaurant.getMenu();
        item.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        menuList.setItems(menu.getAllItems());
    }

    /**
     * Add menu item to the menu
     *
     */
    @FXML
    private void addMenuItem() { dbc.createMenuItem(); }

    /**
     * Delete menu item from menu
     *
     */
    @FXML
    private void deleteMenuItem() {
        MenuItem menuItem = menuList.getSelectionModel().getSelectedItem();
        if (menuItem != null) {
            String message = "Are you sure you want to delete menu item\n"
                    + menuItem.getName() + " (Price: "
                    + menuItem.getPrice() + ")?";
            dbc.displayAlertBox("Cancel Booking", message, menuItem, "menuItem", null, null);
        }
    }

}