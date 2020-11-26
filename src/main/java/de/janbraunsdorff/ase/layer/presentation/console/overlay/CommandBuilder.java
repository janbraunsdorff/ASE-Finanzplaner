package de.janbraunsdorff.ase.layer.presentation.console.overlay;

import de.janbraunsdorff.ase.layer.presentation.console.Command;

public interface CommandBuilder {
    State build(State state, Command command);
}
