package businesslogic.testable;

import businesslogic.Presentation;

public class TestablePresentation extends Presentation {
    private boolean clearCalled = false;
    private int clearCallCount = 0;

    private boolean exitCalled = false;
    private int exitStatusCode = -1;
    private int exitCallCount = 0;

    @Override
    public void clear() {
        clearCalled = true;
        clearCallCount++;
    }


    public boolean wasClearCalled() {
        return clearCalled;
    }

    public int getClearCallCount() {
        return clearCallCount;
    }

    @Override
    public void exit(int status) {
        exitCalled = true;
        exitStatusCode = status;
        exitCallCount++;
    }

    public boolean wasExitCalled() {
        return exitCalled;
    }

    public int getExitStatusCode() {
        return exitStatusCode;
    }

    public int getExitCallCount() {
        return exitCallCount;
    }
}
