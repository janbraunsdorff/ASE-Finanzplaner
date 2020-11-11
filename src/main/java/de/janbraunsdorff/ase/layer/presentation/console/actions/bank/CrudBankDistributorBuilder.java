package de.janbraunsdorff.ase.layer.presentation.console.actions.bank;


import de.janbraunsdorff.ase.layer.presentation.console.actions.Action;

public class CrudBankDistributorBuilder {
    private final CrudBankDistributor distributor;

    public CrudBankDistributorBuilder() {
        this.distributor = new CrudBankDistributor();
    }

    public CrudBankDistributorBuilder addCommand(String command, Action action) {
        this.distributor.addAction(command, action);
        return this;
    }

    public CrudBankDistributor build() {
        return this.distributor;
    }
}
