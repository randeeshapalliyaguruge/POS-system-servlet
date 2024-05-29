package command;

import factories.Manager;

public class ReportManagerCommand implements Command {
    private Manager reportManager;

    public ReportManagerCommand(Manager reportManager) {
        this.reportManager = reportManager;
    }

    @Override
    public void execute() {
        reportManager.manage();
    }
}