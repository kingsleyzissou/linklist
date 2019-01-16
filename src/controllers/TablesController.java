package controllers;

import models.Table;
import java.net.URL;
import models.Restaurant;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;

import static controllers.DashboardController.*;

public class TablesController implements Initializable {

    @FXML private TableView<Table> tableList;
    @FXML private TableColumn<Table, Integer> tableNumber;
    @FXML private TableColumn<Table, String> tableName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurant restaurant = DashboardController.restaurant;
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        // Using method reference
        // Reference https://www.programcreek.com/java-api-examples/index.php?
        // api=javafx.scene.control.TableColumn.CellDataFeatures
        tableName.setCellValueFactory(TablesController::getTableName);
        tableList.setItems(restaurant.getAllTables());
    }

    /**
     * Method converts the table number from a number to a string
     *
     * @param t the object being manipulated
     * @return the string representation of the table id
     */
    private static ObservableValue<String> getTableName(TableColumn.CellDataFeatures<Table, String> t) {
        // Prefixing table id with "Table "
        return new ReadOnlyObjectWrapper<>("Table " + t.getValue().getUuid());
    }

    /**
     * Go to create table view
     *
     */
    @FXML
    public void createTable() {
        dbc.createTable();
    }


    /**
     * View individual table
     *
     */
    @FXML
    private void viewTable() {
        Table table = tableList.getSelectionModel().getSelectedItem();
        if (table != null) dbc.viewTable(table);
    }

    /**
     * Display modal before deleting selected table
     *
     */
    public void deleteTable() {
        Table table = tableList.getSelectionModel().getSelectedItem();
        if (table != null) {
            String message = "Are you sure you want to delete table number "
                    + table.getUuid() + "?";
            dbc.displayAlertBox("Delete Table", message, table, "table", null, null);
        }
    }

}
