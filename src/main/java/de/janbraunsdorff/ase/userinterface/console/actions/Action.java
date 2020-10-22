package de.janbraunsdorff.ase.userinterface.console.actions;

import de.janbraunsdorff.ase.userinterface.console.result.Result;

public interface Action {
    Result act(String command);
}
