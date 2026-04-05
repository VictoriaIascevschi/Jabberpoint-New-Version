package ui;

import org.junit.jupiter.api.Test;

import java.awt.HeadlessException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AboutBoxTest
{
    @Test
    void show_whenRunningHeadless_throwsHeadlessException()
    {
        assertThrows(HeadlessException.class, () -> AboutBox.show(null));
    }
}
