package io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessorTest
{
    @Test
    void getDemoAccessor_whenCalled_returnsDemoPresentationAccessor()
    {
        Accessor accessor = Accessor.getDemoAccessor();

        assertTrue(accessor instanceof DemoPresentation);
    }
}
