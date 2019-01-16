package utilities;

import models.MenuItem;
import javafx.util.StringConverter;
import controllers.DashboardController;

public class MenuItemStringConverter extends StringConverter<MenuItem> {

    /**
     * This method takes in a menu item and converts it to a string (just using the menu item name)
     * This is necessary to display menu items in a combobox in the application
     *
     * @param item the menu item being outputted as a string
     * @return the string representation of the menu item
     */
    @Override
    public String toString(MenuItem item) {
        return item==null? "" : item.getName();
    }

    /**
     * Get a menu item based on it's name.
     * This is necessary to convert a combobox selection back
     * into a menu item.
     *
     * @param name the name of the menu item
     * @return the menu item based on the name
     */
    @Override
    public MenuItem fromString(String name) {
        return DashboardController.restaurant.getMenu().getItemByName(name);
    }

}