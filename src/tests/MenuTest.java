package tests;

import models.Menu;
import models.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;
    private MenuItem pizza;
    private MenuItem fish;
    private MenuItem chips;

    @BeforeEach
    void setUp() {
        this.menu = new Menu();
        this.fish = new MenuItem("Fish", 10.0);
        this.pizza = new MenuItem("Pizza", 12.0);
        this.chips = new MenuItem("Chips", 5.0);
        this.menu.add(fish);
        this.menu.add(chips);
        this.menu.add(pizza);
    }

    @Test
    void getMenuItemByName() {
        assertEquals(menu.getItemByName("Pizza"), pizza);
    }

    @Test
    void menuItemNotInMenuIsNull() {
        assertNull(menu.getItemByName("Burger"));
    }

    @Test
    void deletedItemIsNotFound() {
        assertEquals(menu.getItemByName("Fish"), fish);
        menu.remove(this.fish);
        assertNull(menu.getItemByName("Fish"));
    }

}
