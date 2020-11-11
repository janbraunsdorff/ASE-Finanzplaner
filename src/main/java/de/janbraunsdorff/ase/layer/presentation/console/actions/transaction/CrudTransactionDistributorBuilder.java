package de.janbraunsdorff.ase.layer.presentation.console.actions.transaction;

import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;

public class CrudTransactionDistributorBuilder {
    private final CrudTransactionDistributor distributor;

    public CrudTransactionDistributorBuilder() {
        this.distributor = new CrudTransactionDistributor();
    }

    public CrudTransactionDistributorBuilder addCommand(String command, Action action) {
        this.distributor.addAction(command, action);
        return this;
    }

    public CrudTransactionDistributor build() {
        return this.distributor;
    }
}
