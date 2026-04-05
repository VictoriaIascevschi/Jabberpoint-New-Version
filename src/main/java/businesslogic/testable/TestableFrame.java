package businesslogic.testable;

import java.awt.*;

public class TestableFrame extends Frame {
    private boolean repaintCalled = false;
    private int repaintCallCount = 0;

    @Override
    public void repaint() {
        repaintCalled = true;
        repaintCallCount++;
    }

    public boolean wasRepaintCalled() {
        return repaintCalled;
    }

    public int getRepaintCallCount() {
        return repaintCallCount;
    }
}
