package command;

import factories.Manager;

public class StockManagerCommand implements Command {
    private Manager stockManager;

    public StockManagerCommand(Manager stockManager) {
        this.stockManager = stockManager;
    }

    @Override
    public void execute() {
        stockManager.manage();
    }
}
