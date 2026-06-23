package io;

public class AccessorFactory
{
    public ReadAccessor createReader(String filename)
    {
        return new XMLAccessor();
    }

    public WriteAccessor createWriter(String filename)
    {
        return new XMLAccessor();
    }
}
