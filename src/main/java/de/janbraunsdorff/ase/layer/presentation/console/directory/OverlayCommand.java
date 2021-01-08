package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public record OverlayCommand (ExpertCommand command, StateTransition transition, String ident){

    public OverlayCommand(ExpertCommand command, StateTransition transition) {
        this(command, transition, null);
    }

}
