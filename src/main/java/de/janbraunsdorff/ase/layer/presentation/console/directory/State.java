package de.janbraunsdorff.ase.layer.presentation.console.directory;

import static de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy.*;

public record State(Hierarchy hierarchy, String bankIdent, String accountIdent) {
    public static State createInitState() {
        return new State(Hierarchy.BANK, null, null);
    }

    private State goUp() {
        return switch (this.hierarchy) {
            case BANK, ACCOUNT -> new State(BANK, null, null);
            case TRANSACTION -> new State(ACCOUNT, this.bankIdent, null);
        };
    }

    private State goDeep(String ident) {
        return switch (this.hierarchy) {
            case BANK -> new State(ACCOUNT, ident, null);
            case ACCOUNT -> new State(TRANSACTION, this.bankIdent, ident);
            case TRANSACTION -> new State(TRANSACTION, this.bankIdent, this.accountIdent);
        };
    }

    public State move(OverlayCommand overlayCommand) {
        return switch (overlayCommand.transition()) {
            case DEEPER -> this.goDeep(overlayCommand.ident());
            case UP -> this.goUp();
            default -> this;
        };
    }


    public Hierarchy getHierarchy() {
        return hierarchy;
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
