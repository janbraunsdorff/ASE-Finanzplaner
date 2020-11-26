package de.janbraunsdorff.ase.layer.presentation.console;


import de.janbraunsdorff.ase.layer.presentation.console.action.Result;

public interface Distributor {
    Result distribute(Command command);
}
