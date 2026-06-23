package io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessorTest
{
    @Test
    void getDemoAccessor_whenCalled_returnsDemoPresentationAccessor()
    {
        ReadAccessor accessor = Accessor.getDemoAccessor();

        assertTrue(accessor instanceof DemoPresentation);
    }

    @Test
    void getDemoAccessor_whenCalled_returnsReadOnlyAccessor()
    {
        ReadAccessor accessor = Accessor.getDemoAccessor();

        assertFalse(accessor instanceof WriteAccessor);
    }
}
