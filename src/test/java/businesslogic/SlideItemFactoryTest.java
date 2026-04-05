package businesslogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlideItemFactoryTest
{
    private SlideItemFactory factory;

    @BeforeEach
    void setUp()
    {
        factory = new SlideItemFactory();
    }

    @Test
    void createSlideItem_default_shouldReturnTextItem()
    {
        SlideItem item = factory.createSlideItem(1, "Hello");

        assertTrue(item instanceof TextItem);
        assertEquals(1, item.getLevel());
        assertEquals("Hello", ((TextItem) item).getText());
    }

    @Test
    void createSlideItem_nullContent_shouldReturnEmptyString()
    {
        SlideItem item = factory.createSlideItem(1, null);

        assertEquals("", ((TextItem) item).getText());
    }

    @Test
    void createSlideItem_typeText_shouldReturnTextItem()
    {
        SlideItem item = factory.createSlideItem("text", 2, "Test");

        assertTrue(item instanceof TextItem);
        assertEquals(2, item.getLevel());
        assertEquals("Test", ((TextItem) item).getText());
    }

    @Test
    void createSlideItem_typeImage_shouldReturnBitmapItem()
    {
        SlideItem item = factory.createSlideItem("image", 1, "src/test/resources/test.png");

        assertTrue(item instanceof BitmapItem);
        assertEquals(1, item.getLevel());
        assertEquals("src/test/resources/test.png", ((BitmapItem) item).getName());
    }

    @Test
    void createSlideItem_unknownType_shouldFallbackToTextItem()
    {
        SlideItem item = factory.createSlideItem("unknown", 1, "Hello");

        assertTrue(item instanceof TextItem);
    }

    @Test
    void createSlideItem_typeWithNullContent_shouldBeSafe()
    {
        SlideItem item = factory.createSlideItem("text", 1, null);

        assertEquals("", ((TextItem) item).getText());
    }

    @Test
    void createSlideItem_imageType_shouldBeCaseInsensitive()
    {
        SlideItem upper = factory.createSlideItem("IMAGE", 1, "src/test/resources/test.png");

        assertTrue(upper instanceof BitmapItem);
    }

    @Test
    void createSlideItem_emptyType_shouldFallbackToTextItem()
    {
        SlideItem item = factory.createSlideItem("", 1, "Hello");

        assertTrue(item instanceof TextItem);
    }

    @Test
    void createSlideItem_nullType_shouldFallbackToTextItem()
    {
        SlideItem item = factory.createSlideItem(null, 1, "Hello");

        assertTrue(item instanceof TextItem);
    }
}