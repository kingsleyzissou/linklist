package controllers;

import java.net.URL;
import models.Purchase;
import javafx.fxml.FXML;
import models.Restaurant;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;

public class PurchasesController implements Initializable {

    @FXML TableView<Purchase> settledList;
    @FXML TableColumn<Purchase, String> settledItem;
    @FXML TableColumn<Purchase, Integer> settledQuantity;
    @FXML TableColumn<Purchase, Double> settledPrice;

    @FXML TableView<Purchase> pendingList;
    @FXML TableColumn<Purchase, String> pendingItem;
    @FXML TableColumn<Purchase, Integer> pendingQuantity;
    @FXML TableColumn<Purchase, Double> pendingPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurant tables = DashboardController.restaurant;

        // Using method reference
        settledPrice.setCellValueFactory(PurchasesController::getPrice);
        settledItem.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        settledQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        settledList.setItems(tables.getSettledPurchases());

        // Using method reference
        pendingPrice.setCellValueFactory(PurchasesController::getPrice);
        pendingItem.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        pendingQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pendingList.setItems(tables.getPendingPurchases());
    }

    /**
     * Converts the price to a double, based on the price and the quantity ordered
     *
     * @param t the value being converted
     * @return a double of the price
     */
    private static ObservableValue<Double> getPrice(TableColumn.CellDataFeatures<Purchase, Double> t) {
        // Get the price based on the quantity of menu items times the price of the menu item
        return new ReadOnlyObjectWrapper<>(t.getValue().getMenuItem().getPrice() * t.getValue().getQuantity());
    }

}
