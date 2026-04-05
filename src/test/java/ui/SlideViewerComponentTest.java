package ui;

import businesslogic.Presentation;
import businesslogic.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlideViewerComponentTest
{
    @Mock
    private Presentation presentation;
    @Mock
    private Slide initialSlide;
    @Mock
    private Slide updatedSlide;
    @Mock
    private JFrame frame;

    private SlideViewerComponent component;

    @BeforeEach
    void setUp()
    {
        when(presentation.getCurrentSlide()).thenReturn(initialSlide);
        component = new SlideViewerComponent(presentation, frame);
        component.setSize(1200, 800);
    }

    @Test
    void constructor_whenCreated_setsCurrentSlideFromPresentation()
    {
        assertSame(initialSlide, component.getSlide());
    }

    @Test
    void getPreferredSize_whenCalled_returnsSlideDimensions()
    {
        Dimension preferredSize = component.getPreferredSize();

        assertEquals(new Dimension(Slide.WIDTH, Slide.HEIGHT), preferredSize);
    }

    @Test
    void paintComponent_whenSlideNumberIsNegative_doesNotDrawSlide()
    {
        when(presentation.getSlideNumber()).thenReturn(-1);
        Graphics graphics = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB).createGraphics();

        component.paintComponent(graphics);

        verify(initialSlide, never()).draw(any(Graphics.class), any(Rectangle.class), eq(component));
    }

    @Test
    void paintComponent_whenSlideNumberIsValid_drawsSlide()
    {
        when(presentation.getSlideNumber()).thenReturn(0);
        when(presentation.getSize()).thenReturn(2);
        Graphics graphics = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB).createGraphics();

        component.paintComponent(graphics);

        verify(initialSlide).draw(any(Graphics.class), any(Rectangle.class), eq(component));
    }

    @Test
    void update_whenDataIsNull_keepsCurrentSlide()
    {
        component.update(presentation, null);

        assertSame(initialSlide, component.getSlide());
    }

    @Test
    void update_whenDataProvided_updatesSlideReference()
    {
        when(presentation.getTitle()).thenReturn("Updated Title");

        component.update(presentation, updatedSlide);

        assertSame(updatedSlide, component.getSlide());
    }

    @Test
    void update_whenDataProvided_setsFrameTitle()
    {
        when(presentation.getTitle()).thenReturn("Updated Title");

        component.update(presentation, updatedSlide);

        verify(frame).setTitle("Updated Title");
    }
}
