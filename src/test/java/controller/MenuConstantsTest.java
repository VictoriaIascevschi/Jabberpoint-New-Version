package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuConstantsTest
{
    @Test
    void constructor_whenCreated_createsInstance()
    {
        MenuConstants menuConstants = new MenuConstants();

        assertNotNull(menuConstants);
    }

    @Test
    void constants_whenAccessed_matchExpectedValues()
    {
        assertEquals("File", MenuConstants.FILE);
    }
}
