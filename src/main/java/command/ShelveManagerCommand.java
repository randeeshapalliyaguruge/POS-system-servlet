package command;

import factories.Manager;

public class ShelveManagerCommand implements Command {
    private Manager shelveManager;

    public ShelveManagerCommand(Manager shelveManager) {
        this.shelveManager = shelveManager;
    }

    @Override
    public void execute() {
        shelveManager.manage();
    }
}
