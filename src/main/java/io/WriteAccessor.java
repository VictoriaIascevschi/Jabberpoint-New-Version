package io;

import businesslogic.Presentation;

import java.io.IOException;

public interface WriteAccessor
{
    void saveFile(Presentation presentation, String filename) throws IOException;
}
