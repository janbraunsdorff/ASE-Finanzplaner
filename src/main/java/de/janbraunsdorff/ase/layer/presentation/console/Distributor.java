package de.janbraunsdorff.ase.layer.presentation.console;


import de.janbraunsdorff.ase.layer.presentation.console.result.Result;

public interface Distributor {
    Result distribute(String command);
}
