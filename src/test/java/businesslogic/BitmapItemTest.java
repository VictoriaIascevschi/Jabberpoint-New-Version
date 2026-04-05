package businesslogic;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitmapItemTest
{
    private static final String TEST_IMAGE = "src/test/resources/test.png";
    private static final Style TEST_STYLE = new Style(10, Color.BLACK, 18, 6);

    @Test
    void constructor_shouldStoreLevel()
    {
        BitmapItem item = new BitmapItem(2, TEST_IMAGE);

        assertEquals(2, item.getLevel());
    }

    @Test
    void getName_shouldReturnImagePath()
    {
        BitmapItem item = new BitmapItem(2, TEST_IMAGE);

        assertEquals(TEST_IMAGE, item.getName());
    }

    @Test
    void getBoundingBox_shouldUseIndentLeadingAndImageSize()
    {
        BitmapItem item = new BitmapItem(1, TEST_IMAGE);
        BufferedImage canvas = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();

        Rectangle box = item.getBoundingBox(g2d, null, 1.0f, TEST_STYLE);
        g2d.dispose();

        assertEquals(new Rectangle(10, 0, 121, 167), box);
    }

    @Test
    void draw_shouldPaintPixels()
    {
        BitmapItem item = new BitmapItem(0, TEST_IMAGE);
        BufferedImage canvas = createWhiteCanvas(260, 260);
        Graphics2D g2d = canvas.createGraphics();

        item.draw(0, 0, 1.0f, g2d, TEST_STYLE, null);
        g2d.dispose();

        assertTrue(hasAnyNonWhitePixel(canvas));
    }

    @Test
    void toString_shouldIncludeLevelAndImageName()
    {
        BitmapItem item = new BitmapItem(3, TEST_IMAGE);

        assertEquals("main.java.logic.BitmapItem[3," + TEST_IMAGE + "]", item.toString());
    }

    @Test
    void missingFile_shouldCauseFailureWhenImageDataIsUsed()
    {
        BitmapItem item = new BitmapItem(1, "src/test/resources/does-not-exist.png");
        BufferedImage canvas = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();

        assertThrows(NullPointerException.class, () -> item.getBoundingBox(g2d, null, 1.0f, TEST_STYLE));
        g2d.dispose();
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
