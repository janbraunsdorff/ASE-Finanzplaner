package de.janbraunsdorff.ase.layer.presentation.console.action;


import de.janbraunsdorff.ase.layer.presentation.console.Command;

public interface UseCase {
    Result act(Command command) throws Exception;
}
