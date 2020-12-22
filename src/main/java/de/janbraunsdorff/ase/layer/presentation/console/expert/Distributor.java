package de.janbraunsdorff.ase.layer.presentation.console.expert;


import de.janbraunsdorff.ase.layer.presentation.console.expert.action.Result;

public interface Distributor {
    Result distribute(ExpertCommand command);
}
