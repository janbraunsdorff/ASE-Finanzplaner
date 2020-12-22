package de.janbraunsdorff.ase.layer.presentation.console.expert.action;


import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

public interface UseCase {
    Result act(ExpertCommand command) throws Exception;
}
