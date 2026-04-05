package io;

import businesslogic.BitmapItem;
import businesslogic.Presentation;
import businesslogic.Slide;
import businesslogic.SlideItem;
import businesslogic.TextItem;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XMLAccessorTest
{
    @Test
    void loadFile_whenValidXmlProvided_setsPresentationTitle() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        Path xmlFile = createTempXmlFile("""
                <?xml version="1.0"?>
                <presentation>
                    <showtitle>My Show</showtitle>
                    <slide>
                        <title>Slide 1</title>
                        <item kind="text" level="1">Hello</item>
                    </slide>
                </presentation>
                """);

        xmlAccessor.loadFile(presentation, xmlFile.toString());

        assertEquals("My Show", presentation.getTitle());
    }

    @Test
    void loadFile_whenValidXmlProvided_loadsSlides() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        Path xmlFile = createTempXmlFile("""
                <?xml version="1.0"?>
                <presentation>
                    <showtitle>My Show</showtitle>
                    <slide>
                        <title>Slide 1</title>
                        <item kind="text" level="1">Hello</item>
                    </slide>
                    <slide>
                        <title>Slide 2</title>
                        <item kind="text" level="2">World</item>
                    </slide>
                </presentation>
                """);

        xmlAccessor.loadFile(presentation, xmlFile.toString());

        assertEquals(2, presentation.getSize());
    }

    @Test
    void loadFile_whenMalformedXmlProvided_keepsPresentationEmpty() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        Path xmlFile = createTempXmlFile("<presentation><showtitle>Broken");

        xmlAccessor.loadFile(presentation, xmlFile.toString());

        assertEquals(0, presentation.getSize());
    }

    @Test
    void loadSlideItem_whenTextTypeProvided_appendsTextItem() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Slide slide = new Slide();
        Element item = createItemElement("text", "2", "Line");

        xmlAccessor.loadSlideItem(slide, item);

        assertTrue(slide.getSlideItem(0) instanceof TextItem);
    }

    @Test
    void loadSlideItem_whenImageTypeProvided_appendsBitmapItem() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Slide slide = new Slide();
        Element item = createItemElement("image", "1", "src/test/resources/test.png");

        xmlAccessor.loadSlideItem(slide, item);

        assertTrue(slide.getSlideItem(0) instanceof BitmapItem);
    }

    @Test
    void loadSlideItem_whenUnknownTypeProvided_doesNotAppendItem() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Slide slide = new Slide();
        Element item = createItemElement("video", "1", "movie.mp4");

        xmlAccessor.loadSlideItem(slide, item);

        assertEquals(0, slide.getSize());
    }

    @Test
    void loadSlideItem_whenInvalidLevelProvided_usesDefaultLevel() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Slide slide = new Slide();
        Element item = createItemElement("text", "not-a-number", "Fallback level");

        xmlAccessor.loadSlideItem(slide, item);

        assertEquals(1, slide.getSlideItem(0).getLevel());
    }

    @Test
    void saveFile_whenPresentationProvided_writesPresentationTag() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = createPresentationWithItems();
        Path outputFile = Files.createTempFile("jabberpoint-save", ".xml");

        xmlAccessor.saveFile(presentation, outputFile.toString());
        String content = Files.readString(outputFile);

        assertTrue(content.contains("<presentation>"));
    }

    @Test
    void saveFile_whenPresentationProvided_writesTextItem() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = createPresentationWithItems();
        Path outputFile = Files.createTempFile("jabberpoint-save", ".xml");

        xmlAccessor.saveFile(presentation, outputFile.toString());
        String content = Files.readString(outputFile);

        assertTrue(content.contains("<item kind=\"text\" level=\"1\">Some text</item>"));
    }

    @Test
    void saveFile_whenPresentationProvided_writesImageItem() throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = createPresentationWithItems();
        Path outputFile = Files.createTempFile("jabberpoint-save", ".xml");

        xmlAccessor.saveFile(presentation, outputFile.toString());
        String content = Files.readString(outputFile);

        assertTrue(content.contains("<item kind=\"image\" level=\"1\">src/test/resources/test.png</item>"));
    }

    private Path createTempXmlFile(String xmlContent) throws Exception
    {
        Path file = Files.createTempFile("jabberpoint-xml", ".xml");
        Files.writeString(file, xmlContent);
        return file;
    }

    private Element createItemElement(String kind, String level, String content) throws Exception
    {
        String xml = "<item kind=\"" + kind + "\" level=\"" + level + "\">" + content + "</item>";
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new java.io.ByteArrayInputStream(xml.getBytes()));
        return document.getDocumentElement();
    }

    private Presentation createPresentationWithItems()
    {
        Presentation presentation = new Presentation();
        presentation.setTitle("Saved Show");

        Slide slide = new Slide();
        slide.setTitle("Slide Title");
        slide.append(new TextItem(1, "Some text"));
        slide.append(new BitmapItem(1, "src/test/resources/test.png"));
        presentation.append(slide);
        return presentation;
    }
}
