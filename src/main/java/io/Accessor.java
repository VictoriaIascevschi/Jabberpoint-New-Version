package io;

/**
 * <p>Holds shared accessor constants and factories.</p>
 * <p>Use ReadAccessor and WriteAccessor for loading and saving behaviour.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public final class Accessor
{
    public static final String DEMO_NAME = "Demonstration presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

	private Accessor()
	{

	}

    public static ReadAccessor getDemoAccessor()
    {
        return new DemoPresentation();
    }
}
