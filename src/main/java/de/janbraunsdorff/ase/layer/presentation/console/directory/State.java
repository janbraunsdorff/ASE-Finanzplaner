package de.janbraunsdorff.ase.layer.presentation.console.directory;

import static de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy.*;

public class State {
    private final Hierarchy hierarchy;
    private final String bankIdent;
    private final String accountIdent;

    public static State createInitState() {
        return new State(Hierarchy.BANK, null, null);
    }

    private State(Hierarchy hierarchy, String bankIdent, String accountIdent) {
        this.hierarchy = hierarchy;
        this.bankIdent = bankIdent;
        this.accountIdent = accountIdent;
    }

    private State goUp() {
        switch (this.hierarchy) {
            case BANK:
            case ACCOUNT:
                return new State(BANK, null, null);
            case TRANSACTION:
                return new State(ACCOUNT, this.bankIdent, null);
        }
        throw new IllegalArgumentException();
    }

    private State goDeep(String ident) {
        switch (this.hierarchy) {
            case BANK:
                return new State(ACCOUNT, ident, null);
            case ACCOUNT:
                return new State(TRANSACTION, this.bankIdent, ident);
            case TRANSACTION:
                return new State(TRANSACTION, this.bankIdent, this.accountIdent);
        }
        throw new IllegalArgumentException();
    }

    public State move(OverlayCommand overlayCommand){
        switch (overlayCommand.getTransition()) {
            case DEEPER:
                return this.goDeep(overlayCommand.getIdent());
            case UP:
                return this.goUp();
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
