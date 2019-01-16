package models;

/**
 * Purchase class
 *
 * Class to manage individual orders and purchases
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Purchase {

    private MenuItem menuItem;
    private  int quantity;

    public Purchase(MenuItem menuItem, int quantity) {
        this.setMenuItem(menuItem);
        this.setQuantity(quantity);
    }

    /**
     * Get the menu item being purchased
     *
     * @return the menu item being purchased
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Set the menu item being purchased
     *
     * @param menuItem the item being purchased
     */
    private void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * Get the quantity purchased of a specific menuItem;
     *
     * @return the quantity being purchased of a menu item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of an item being purchased
     *
     * @param quantity the quantity of orders for a menu item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null) return false;
        if(!(object instanceof  Purchase)) return false;
        Purchase purchase = (Purchase) object;
        return this.getMenuItem().equals(purchase.getMenuItem());
    }

}
