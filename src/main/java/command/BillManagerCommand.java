package command;

import factories.Manager;

public class BillManagerCommand implements Command {
    private Manager billManager;

    public BillManagerCommand(Manager billManager) {
        this.billManager = billManager;
    }

    @Override
    public void execute() {
        billManager.manage();
    }
}