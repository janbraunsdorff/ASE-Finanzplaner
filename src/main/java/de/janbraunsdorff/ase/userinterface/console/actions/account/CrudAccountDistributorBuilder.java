package de.janbraunsdorff.ase.userinterface.console.actions.account;

import de.janbraunsdorff.ase.userinterface.console.actions.Action;

public class CrudAccountDistributorBuilder {
    private final CrudAccountDistributor distributor;

    public CrudAccountDistributorBuilder(){
        this.distributor = new CrudAccountDistributor();
    }

    public CrudAccountDistributorBuilder addCommand(String command, Action action){
        this.distributor.addAction(command, action);
        return this;
    }

    public CrudAccountDistributor build(){
        return this.distributor;
    }
}