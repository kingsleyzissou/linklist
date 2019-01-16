package controllers;

import java.net.URL;

import models.Table;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import static controllers.DashboardController.*;

public class TableController implements Initializable {

    private Table table;

    @FXML private Label number;
    @FXML private Label seats;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    /**
     * Set the required data for the controller
     *
     * @param table the table being viewed
     */
    void setData(Table table) {
        this.table = table;
        number.setText("" + table.getUuid());
        seats.setText("" + table.getSeats());
    }

    /**
     * Go back to view all tables
     *
     */
    public void goBack() {
        dbc.viewTables();
    }

    /**
     * Display message before deleting table
     *
     */
    public void deleteTable() {
        String message = "Are you sure you want to delete table number "
                + this.table.getUuid() + "?";
        dbc.displayAlertBox("Delete Table", message, table, "table", null, null);
    }

}
