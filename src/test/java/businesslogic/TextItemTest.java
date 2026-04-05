package businesslogic;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextItemTest
{
    private static final Style TEST_STYLE = new Style(12, Color.BLACK, 20, 8);

    @Test
    void constructor_shouldStoreLevel()
    {
        TextItem item = new TextItem(2, "Hello");
        assertEquals(2, item.getLevel());
    }

    @Test
    void constructor_shouldStoreText()
    {
        TextItem item = new TextItem(2, "Hello");
        assertEquals("Hello", item.getText());
    }

    @Test
    void getText_whenNull_shouldReturnEmptyString()
    {
        TextItem item = new TextItem(1, null);

        assertEquals("", item.getText());
    }

    @Test
    void defaultConstructor_shouldUseLevelZero()
    {
        TextItem item = new TextItem();
        assertEquals(0, item.getLevel());
    }

    @Test
    void defaultConstructor_shouldUseNoTextGiven()
    {
        TextItem item = new TextItem();
        assertEquals("No Text Given", item.getText());
    }

    @Test
    void getAttributedString_shouldContainScaledFontAttribute()
    {
        TextItem item = new TextItem(1, "Attr");
        float scale = 1.5f;

        AttributedCharacterIterator iterator = item.getAttributedString(TEST_STYLE, scale).getIterator();
        iterator.first();

        assertEquals(TEST_STYLE.getFont(scale), iterator.getAttribute(TextAttribute.FONT));
    }

    @Test
    void getBoundingBox_shouldUseIndent()
    {
        TextItem item = new TextItem(1, "Hello world");
        BufferedImage canvas = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();
        Rectangle box = item.getBoundingBox(g2d, null, 1.0f, TEST_STYLE);
        g2d.dispose();

        assertEquals(12, box.x);
    }

    @Test
    void getBoundingBox_shouldHavePositiveHeight()
    {
        TextItem item = new TextItem(1, "Hello world");
        BufferedImage canvas = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();
        Rectangle box = item.getBoundingBox(g2d, null, 1.0f, TEST_STYLE);
        g2d.dispose();

        assertTrue(box.height > 0);
    }

    @Test
    void draw_whenTextIsEmpty_shouldNotPaintPixels()
    {
        TextItem item = new TextItem(1, "");
        BufferedImage canvas = createWhiteCanvas(200, 120);
        Graphics2D g2d = canvas.createGraphics();
        item.draw(0, 0, 1.0f, g2d, TEST_STYLE, null);
        g2d.dispose();

        assertFalse(hasAnyNonWhitePixel(canvas));
    }

    @Test
    void draw_whenTextIsPresent_shouldPaintPixels()
    {
        TextItem item = new TextItem(1, "Hello");
        BufferedImage canvas = createWhiteCanvas(300, 180);
        Graphics2D g2d = canvas.createGraphics();
        item.draw(0, 0, 1.0f, g2d, TEST_STYLE, null);
        g2d.dispose();

        assertTrue(hasAnyNonWhitePixel(canvas));
    }

    @Test
    void toString_shouldIncludeLevelAndText()
    {
        TextItem item = new TextItem(2, "Hello");

        assertEquals("main.java.logic.TextItem[2,Hello]", item.toString());
    }

    private BufferedImage createWhiteCanvas(int width, int height)
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return image;
    }

    private boolean hasAnyNonWhitePixel(BufferedImage image)
    {
        int white = Color.WHITE.getRGB();
        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                if (image.getRGB(x, y) != white)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
