package models;

/**
 * MenuItem class
 *
 * Class to create and manage individual menu items
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class MenuItem {

    private String name;
    private Double price;

    public MenuItem(String name, Double price) {
        this.setName(name);
        this.setPrice(price);
    }

    /**
     * Get the name of the menu item
     *
     * @return name of menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the menu item
     *
     * @param name the name of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of the menu item
     *
     * @return price of the menu item
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the price of a menu item
     *
     * @param price the price of the menu item
     */
    private void setPrice(Double price) {
        this.price = price;
    }


    /**
     * When returning a string representation, only return the name
     * of the menu item and not the price
     *
     * @return a string representation of only the menu item name
     */
    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * Compare two menu items based on the name of the items
     *
     * @param object being compared against the menu item
     * @return boolean of whether or not the object is the same as the menu item
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if(object instanceof String)
            return this.getName().toUpperCase().equals(((String) object).toUpperCase());
        if(!(object instanceof MenuItem)) return false;
        MenuItem item = (MenuItem) object;
        return this.getName().toUpperCase().equals(item.getName().toUpperCase());
    }

}
