package businesslogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlideItemTest
{
    private TextItem item;

    @BeforeEach
    void setUp()
    {
        item = new TextItem(2, "Hello");
    }

    @Test
    void getText_shouldReturnCorrectText()
    {
        assertEquals("Hello", item.getText());
    }

    @Test
    void getText_whenNull_shouldReturnEmptyString()
    {
        TextItem nullItem = new TextItem(1, null);

        assertEquals("", nullItem.getText());
    }

    @Test
    void constructor_shouldSetLevel()
    {
        assertEquals(2, item.getLevel());
    }

    @Test
    void defaultConstructor_shouldUseDefaultValues()
    {
        TextItem defaultItem = new TextItem();

        assertEquals(0, defaultItem.getLevel());
        assertEquals("No Text Given", defaultItem.getText());
    }

    @Test
    void toString_shouldContainLevelAndText()
    {
        String result = item.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("Hello"));
    }

    @Test
    void getAttributedString_shouldNotBeNull()
    {
        Style style = new Style(0, java.awt.Color.BLACK, 10, 10);
        assertNotNull(item.getAttributedString(style, 1.0f));
    }
}