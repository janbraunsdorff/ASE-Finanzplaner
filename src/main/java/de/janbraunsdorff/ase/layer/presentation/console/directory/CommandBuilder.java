package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public interface CommandBuilder {
    State build(State state, Command command);
}
