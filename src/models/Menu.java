package models;

import collections.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Menu class
 *
 * A list to manage all individual menu items
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Menu {

    private LinkedList<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new LinkedList<>();
    }

    /**
     * Add a menuItems item to the menuItems
     *
     * @param menuItem menuItems item to be added to the menuItems
     */
    public void add(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    /**
     * Remove a menuItems item from the menuItems
     *
     * @param menuItem menuItems item to be removed
     */
    public void remove(MenuItem menuItem) {
        this.menuItems.delete(menuItem);
    }

    /**
     * Get all the menu items from the menu
     *
     * @return all the menu items in the menu
     */
    public LinkedList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Get a menu item from the name of the item
     *
     * @param name the name of the item being searched for
     * @return the menu item object that is returned
     */
    public MenuItem getItemByName(String name) {

        for(MenuItem item : this.menuItems) {
            if(item.getName().equals(name)) return item;
        }

        return null;

    }

    /**
     * This method returns an observable list of menu items for
     * use in JavaFX views
     *
     * @return menuItems list
     */
    public ObservableList<MenuItem> getAllItems() {

        ObservableList<MenuItem> menuList = FXCollections.observableArrayList();

        for(MenuItem item : menuItems) {
            menuList.add(item);
        }

        return menuList;

    }

}
