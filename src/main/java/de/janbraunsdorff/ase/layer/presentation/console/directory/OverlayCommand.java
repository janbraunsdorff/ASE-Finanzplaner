package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public final class OverlayCommand {
    private final ExpertCommand command;
    private final StateTransition transition;
    private final String ident;

    public OverlayCommand(ExpertCommand command, StateTransition transition, String ident) {
        this.command = command;
        this.transition = transition;
        this.ident = ident;
    }

    public OverlayCommand(ExpertCommand command, StateTransition transition) {
        this.command = command;
        this.transition = transition;
        this.ident = null;
    }

    public ExpertCommand getCommand() {
        return command;
    }

    public StateTransition getTransition() {
        return transition;
    }

    public String getIdent() {
        return ident;
    }
}
