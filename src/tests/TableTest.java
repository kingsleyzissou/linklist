package tests;

import models.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TableTest {
    private Table table;

    @BeforeEach
    void setUp() {
        table = new Table(1, 4);
    }

    @Test
    void newTableHasAllEmptyBookingSlots() {
        for(Boolean slot : table.getSlots()) {
            assertFalse(slot);
        }
    }



}
