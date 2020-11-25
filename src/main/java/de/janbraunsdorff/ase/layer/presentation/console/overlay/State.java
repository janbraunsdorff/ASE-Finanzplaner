package de.janbraunsdorff.ase.layer.presentation.console.overlay;


import static de.janbraunsdorff.ase.layer.presentation.console.overlay.Hierarchy.*;

public class State {
    private final Hierarchy hierarchy;
    private final String bankIdent;
    private final String accountIdent;
    private final String command;

    public State(Hierarchy hierarchy, String bankIdent, String accountIdent, String command) {
        this.hierarchy = hierarchy;
        this.bankIdent = bankIdent;
        this.accountIdent = accountIdent;
        this.command = command;
    }

    public State goUp(String command) {
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

    public State goDeep(String ident, String command) {
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

    public State stay(String command) {
        return new State(this.hierarchy, this.bankIdent, this.accountIdent, command);
    }

    public String getCommand() {
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
}
