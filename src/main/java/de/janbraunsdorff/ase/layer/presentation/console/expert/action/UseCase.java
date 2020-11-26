package de.janbraunsdorff.ase.layer.presentation.console.expert.action;


import de.janbraunsdorff.ase.layer.presentation.console.expert.Command;

public interface UseCase {
    Result act(Command command) throws Exception;
}
