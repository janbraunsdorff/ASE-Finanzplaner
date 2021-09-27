package de.janbraunsdorff.ase.layer.presentation.console.directory.transaction;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class GroupTransactionTest {
    @Test
    public void build(){
        var builder = new GroupTransaction();
        State sate = new State(Hierarchy.TRANSACTION, "bank", "account");
        ExpertCommand command = new ExpertCommand("group", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("transaction group -a account"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));
    }
}
