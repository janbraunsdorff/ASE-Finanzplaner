package de.janbraunsdorff.ase.layer.presentation.console.overlay;

import de.janbraunsdorff.ase.layer.presentation.console.Command;

import static de.janbraunsdorff.ase.layer.presentation.console.overlay.Hierarchy.*;

public class State {
    private final Hierarchy hierarchy;
    private final String bankIdent;
    private final String accountIdent;
    private final Command command;

    public State(Hierarchy hierarchy, String bankIdent, String accountIdent, Command command) {
        this.hierarchy = hierarchy;
        this.bankIdent = bankIdent;
        this.accountIdent = accountIdent;
        this.command = command;
    }

    public State goUp(Command command) {
        switch (this.hierarchy) {
            case BANK:
                return new State(BANK, null, null, command);
            case ACCOUNT:
                return new State(BANK, this.bankIdent, null, command);
            case Transaction:
                return new State(ACCOUNT, this.bankIdent, this.accountIdent, command);
        }
        throw new IllegalArgumentException();
    }

    public State goDeep(String ident, Command command) {
        switch (this.hierarchy) {
            case BANK:
                return new State(ACCOUNT, ident, null, command);
            case ACCOUNT:
                return new State(Transaction, this.bankIdent, ident, command);
            case Transaction:
                return new State(Transaction, this.bankIdent, this.accountIdent, command);
        }
        throw new IllegalArgumentException();
    }

    public State stay(Command command) {
        return new State(this.hierarchy, this.bankIdent, this.accountIdent, command);
    }

    public Command getCommand() {
        return command;
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

        if(this.bankIdent != null){
            builder.append(this.bankIdent);
            builder.append("/");
        }

        if (this.accountIdent != null){
            builder.append(this.accountIdent);
            builder.append("/");
        }

        if (this.hierarchy == Transaction){
            builder.append("Transaktionen");
            builder.append("/");
        }

        return builder.toString();
    }
}
