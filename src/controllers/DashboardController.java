package controllers;

import models.*;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class DashboardController implements Initializable {

    static Stage modal;

    public static Restaurant restaurant;
    public static DashboardController dbc;

    @FXML private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbc = this;
        restaurant = new Restaurant();
        this.loadHomeScene();
    }

    @FXML
    void loadHomeScene() {
        try {
            Parent homeScene = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
            borderPane.setCenter(homeScene);
        } catch(IOException e) {
            System.out.println("Some exception was thrown");
        }
    }

    @FXML
    public void viewTables() {
        try {
            Parent tableView = FXMLLoader.load(getClass().getResource("/views/Tables.fxml"));
            borderPane.setCenter(tableView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }

    @FXML
    public void viewBookings() {
        try {
            Parent bookingView = FXMLLoader.load(getClass().getResource("/views/Bookings.fxml"));
            borderPane.setCenter(bookingView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown");
        }
    }

    @FXML
    private void viewPurchases() {
        try {
            Parent purchaseView = FXMLLoader.load(getClass().getResource("/views/Purchases.fxml"));
            borderPane.setCenter(purchaseView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown");
        }
    }

    @FXML
    public void viewMenu() {
        try {
            Parent menuView = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            borderPane.setCenter(menuView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown");
        }
    }

    void viewOrders(Booking booking) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Orders.fxml"));
            Parent ordersView = loader.load();
            OrdersController controller = loader.getController();
            Table table = restaurant.getTableById(booking.getTableNumber());
            controller.setData(table, booking);
            this.borderPane.setCenter(ordersView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }

    @FXML
    void displayAlertBox(String title, String message, Object object, String type, Booking booking, Table table) {
        try {
            modal =  new Stage();
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.setTitle(title);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AlertBox.fxml"));
            Parent alertBox = loader.load();
            AlertBoxController controller = loader.getController();
            controller.setData(message, object, type, booking, table);
            modal.setScene(new Scene(alertBox));
            modal.showAndWait();
        } catch(IOException e) {
            System.out.println("Some exception was thrown");
        }
    }

    void viewTable(Table table) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Table.fxml"));
            Parent tableView = loader.load();
            TableController controller = loader.getController();
            controller.setData(table);
            this.borderPane.setCenter(tableView);
        } catch (IOException e) {
            System.out.println("Some exception was thrown over here\n" + e.getMessage());
        }
    }

    void viewBooking(Booking booking) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Booking.fxml"));
            Parent bookingView = loader.load();
            BookingController controller = loader.getController();
            controller.setData(booking, restaurant.getTableById(booking.getTableNumber()));
            this.borderPane.setCenter(bookingView);
        } catch (IOException e) {
            System.out.println("Some exception was thrown over here\n" + e.getMessage());
        }
    }

    void createMenuItem() {
        try {
            Parent createMenuItemView = FXMLLoader.load(getClass().getResource("/views/CreateMenuItem.fxml"));
            this.borderPane.setCenter(createMenuItemView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }

    void createOrder(Table table, Booking booking) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/CreateOrder.fxml"));
            Parent createOrderView = loader.load();
            CreateOrderController controller = loader.getController();
            controller.setData(table, booking);
            this.borderPane.setCenter(createOrderView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }

    void createTable() {
        try {
            Parent createTableView = FXMLLoader.load(getClass().getResource("/views/CreateTable.fxml"));
            this.borderPane.setCenter(createTableView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }

    void createBooking() {
        try {
            Parent createBookingView = FXMLLoader.load(getClass().getResource("/views/CreateBooking.fxml"));
            this.borderPane.setCenter(createBookingView);
        } catch(IOException e) {
            System.out.println("Some exception was thrown\n" + e.getMessage());
        }
    }
}
