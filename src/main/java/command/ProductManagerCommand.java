package command;

import factories.Manager;

public class ProductManagerCommand implements Command {
    private Manager productManager;

    public ProductManagerCommand(Manager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void execute() {
        productManager.manage();
    }
}
