package app;

import org.junit.jupiter.api.Test;

import java.awt.HeadlessException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JabberPointTest {

    @Test
    void main_whenRunningHeadless_throwsHeadlessException() {
        assertThrows(HeadlessException.class, () -> JabberPoint.main(new String[]{}));
    }
}
