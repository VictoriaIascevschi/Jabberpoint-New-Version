package businesslogic;

public class SlideItemFactory
{
    private static final String TEXT = "text";
    private static final String IMAGE = "image";

    public SlideItem createSlideItem(int level, String content)
    {
        String safeContent = content == null ? "" : content;

        return new TextItem(level, safeContent);
    }

    public SlideItem createSlideItem(String type, int level, String content)
    {
        String safeContent = content == null ? "" : content;

        if (TEXT.equalsIgnoreCase(type))
        {
            return new TextItem(level, safeContent);
        }
        
        if (IMAGE.equalsIgnoreCase(type))
        {
            return new BitmapItem(level, safeContent);
        }

        return createSlideItem(level, safeContent);
    }
}
