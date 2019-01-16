package controllers;

import java.net.URL;
import models.Table;
import models.Booking;
import models.Purchase;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import static controllers.DashboardController.*;

public class OrdersController implements Initializable {

    private Table table;
    private Booking booking;

    @FXML private TableView<Purchase> orderList;
    @FXML private TableColumn<Purchase, String> item;
    @FXML private TableColumn<Purchase, Integer> quantity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * Set table needed for the controller
     *
     * @param table the table for the current booking
     * @param booking the current booking at the table
     */
    void setData(Table table, Booking booking) {
        this.table = table;
        this.booking = booking;
        item.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderList.setItems(table.getOrderItems());
    }

    /**
     * Delete order from booking
     *
     */
    @FXML
    public void deleteOrder() {
        Purchase order = orderList.getSelectionModel().getSelectedItem();
        String message = "Are you sure you want to delete the order for\n"
                + order.getMenuItem().getName() + " (Qty: "
                + order.getQuantity() + ")?";
        dbc.displayAlertBox("Delete Order", message, order, "order", booking, table);
    }

    /**
     * Go to create order view
     *
     */
    @FXML
    public void createOrder() { dbc.createOrder(this.table, this.booking); }

    /**
     * Go back to view bookings
     *
     */
    @FXML
    public void goBack() { dbc.viewBooking(this.booking); }

}
