package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

import static de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy.*;

public class State {
    private final Hierarchy hierarchy;
    private final String bankIdent;
    private final String accountIdent;
    private final ExpertCommand command;

    public static State createInitState() {
        return new State(Hierarchy.BANK, null, null, null);
    }

    private State(Hierarchy hierarchy, String bankIdent, String accountIdent, ExpertCommand command) {
        this.hierarchy = hierarchy;
        this.bankIdent = bankIdent;
        this.accountIdent = accountIdent;
        this.command = command;
    }

    private State goUp(ExpertCommand command) {
        switch (this.hierarchy) {
            case BANK:
            case ACCOUNT:
                return new State(BANK, null, null, command);
            case TRANSACTION:
                return new State(ACCOUNT, this.bankIdent, null, command);
        }
        throw new IllegalArgumentException();
    }

    private State goDeep(String ident, ExpertCommand command) {
        switch (this.hierarchy) {
            case BANK:
                return new State(ACCOUNT, ident, null, command);
            case ACCOUNT:
                return new State(TRANSACTION, this.bankIdent, ident, command);
            case TRANSACTION:
                return new State(TRANSACTION, this.bankIdent, this.accountIdent, command);
        }
        throw new IllegalArgumentException();
    }

    public State move(OverlayCommand overlayCommand){
        switch (overlayCommand.getTransition()) {
            case DEEPER:
                return this.goDeep(overlayCommand.getIdent(), overlayCommand.getCommand());
            case UP:
                return this.goUp(overlayCommand.getCommand());
            default:
                return this;
        }
    }


    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public String getBankIdent() {
        return bankIdent;
    }

    public String getAccountIdent() {
        return accountIdent;
    }

    public String getPath() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(this.hierarchy.toString().charAt(0));
        builder.append(this.hierarchy.toString().substring(1).toLowerCase());
        builder.append("]");
        builder.append("/");

        if (this.bankIdent != null) {
            builder.append(this.bankIdent);
            builder.append("/");
        }

        if (this.accountIdent != null) {
            builder.append(this.accountIdent);
            builder.append("/");
        }

        return builder.toString();
    }


}
