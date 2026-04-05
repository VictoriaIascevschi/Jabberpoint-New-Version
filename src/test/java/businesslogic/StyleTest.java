package businesslogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Font;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StyleTest
{
    @BeforeEach
    void setUp()
    {
        Style.createStyles();
    }

    @Test
    void constructor_shouldStoreIndent()
    {
        Style style = new Style(15, Color.BLACK, 22, 9);
        assertEquals(15, style.getIndent());
    }

    @Test
    void constructor_shouldStoreColor()
    {
        Style style = new Style(15, Color.BLUE, 22, 9);
        assertSame(Color.BLUE, style.getColor());
    }

    @Test
    void constructor_shouldStoreFontSize()
    {
        Style style = new Style(15, Color.BLACK, 22, 9);
        assertEquals(22, style.getFontSize());
    }

    @Test
    void constructor_shouldStoreLeading()
    {
        Style style = new Style(15, Color.BLACK, 22, 9);
        assertEquals(9, style.getLeading());
    }

    @Test
    void getFont_shouldReturnBoldFont()
    {
        Style style = new Style(10, Color.BLACK, 18, 8);
        Font font = style.getFont();

        assertTrue(font.isBold());
    }

    @Test
    void getFont_withScale_shouldReturnScaledSize()
    {
        Style style = new Style(10, Color.BLACK, 20, 8);
        Font scaledFont = style.getFont(1.5f);

        assertEquals(30.0f, scaledFont.getSize2D());
    }

    @Test
    void getStyle_shouldReturnConfiguredStyleForLevelZero()
    {
        Style style = Style.getStyle(0);

        assertEquals(0, style.getIndent());
    }

    @Test
    void getStyle_whenLevelTooHigh_shouldReturnLastStyle()
    {
        Style style = Style.getStyle(999);

        assertEquals(90, style.getIndent());
    }
}
