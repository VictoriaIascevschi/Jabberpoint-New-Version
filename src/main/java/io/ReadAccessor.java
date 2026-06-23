package io;

import businesslogic.Presentation;

import java.io.IOException;

public interface ReadAccessor
{
    void loadFile(Presentation presentation, String filename) throws IOException;
}
