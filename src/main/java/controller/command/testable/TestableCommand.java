package controller.command.testable;

import controller.command.Command;

public class TestableCommand implements Command {
    private boolean executed = false;
    private int executionCount = 0;

    @Override
    public void execute() {
        executed = true;
        executionCount++;
    }

    public boolean wasExecuted() {
        return executed;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void reset() {
        executed = false;
        executionCount = 0;
    }
}


