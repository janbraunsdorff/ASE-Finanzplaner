package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public interface CommandBuilder {
    OverlayCommand build(State state, ExpertCommand command);
}
