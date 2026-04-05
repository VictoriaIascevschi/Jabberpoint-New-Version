package businesslogic;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest
{
    @Test
    void constructor_whenCreated_initializesEmptySlide()
    {
        Slide slide = new Slide();

        assertEquals(0, slide.getSize());
    }

    @Test
    void setTitle_whenCalled_updatesTitle()
    {
        Slide slide = new Slide();

        slide.setTitle("My Slide");

        assertEquals("My Slide", slide.getTitle());
    }

    @Test
    void append_whenSlideItemProvided_increasesSize()
    {
        Slide slide = new Slide();

        slide.append(new TextItem(1, "Hello"));

        assertEquals(1, slide.getSize());
    }

    @Test
    void append_whenTypeLevelContentProvided_createsAndAddsSlideItem()
    {
        Slide slide = new Slide();

        slide.append("text", 2, "Created by factory");

        assertTrue(slide.getSlideItem(0) instanceof TextItem);
    }

    @Test
    void getSlideItem_whenValidIndexProvided_returnsExpectedItem()
    {
        Slide slide = new Slide();
        SlideItem expected = new TextItem(1, "Item");
        slide.append(expected);

        SlideItem actual = slide.getSlideItem(0);

        assertSame(expected, actual);
    }

    @Test
    void getSlideItems_whenCalled_returnsAllItemsVector()
    {
        Slide slide = new Slide();
        slide.append(new TextItem(1, "One"));

        assertEquals(1, slide.getSlideItems().size());
    }
}
