package de.janbraunsdorff.ase.layer.presentation.console.directory.account;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import de.janbraunsdorff.ase.layer.presentation.console.directory.Hierarchy;
import de.janbraunsdorff.ase.layer.presentation.console.directory.OverlayCommand;
import de.janbraunsdorff.ase.layer.presentation.console.directory.State;
import de.janbraunsdorff.ase.layer.presentation.console.directory.StateTransition;
import de.janbraunsdorff.ase.layer.presentation.console.expert.ExpertCommand;

class ListAccountTest {
    @Test
    public void build(){
        var builder = new ListAccount();
        State sate = new State(Hierarchy.ACCOUNT, "bank", null);
        ExpertCommand command = new ExpertCommand("ls", 1);
        OverlayCommand res = builder.build(sate, command);

        assertThat(res.command().getInput(), Matchers.is("account all -a bank"));
        assertThat(res.transition(), Matchers.is(StateTransition.STAY));

    }
}
