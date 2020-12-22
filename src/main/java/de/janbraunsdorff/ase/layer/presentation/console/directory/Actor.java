package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public interface Actor {
    OverlayCommand act(State state, ExpertCommand command);

    void addBuilder(String trigger, CommandBuilder builder);
}
