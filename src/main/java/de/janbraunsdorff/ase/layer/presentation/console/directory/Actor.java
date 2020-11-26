package de.janbraunsdorff.ase.layer.presentation.console.directory;

import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public interface Actor {
    State act(State state, Command command);
}
