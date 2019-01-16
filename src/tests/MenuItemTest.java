package tests;

import models.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class MenuItemTest {

    private MenuItem item;

    @BeforeEach
    void setUp() {
        item = new MenuItem("Burger", 10.0);
    }

    @Test
    void menuItemEqualsMethodCanCompareNameAgainstObject(){
        assertTrue(item.equals("Burger"));
    }

    @Test
    void menuItemEqualsMethodIsNotCaseSensitive(){
        assertTrue(item.equals("BURGER"));
    }

    @Test
    void menuItemCanBeComparedToMenuItem(){
        MenuItem burger = new MenuItem("Burger", 10.0);
        assertTrue(item.equals(burger));
    }

}
